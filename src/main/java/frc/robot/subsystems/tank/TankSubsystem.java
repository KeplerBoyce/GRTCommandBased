// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.tank;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TankSubsystem extends SubsystemBase {
    private final TalonGroup left;
    private final TalonGroup right;
    
    //previous target position of the encoders
    private int leftLastEncTarget;
    private int rightLastEncTarget;

    // TODO default drivetankcommand
    public TankSubsystem(int fLeftId, int bLeftId, int fRightId, int bRightId) {
        super();

        left = new TalonGroup(new TalonSRX[] { new TalonSRX(fLeftId), new TalonSRX(bLeftId) });
        left.setNeutralMode(NeutralMode.Brake);

        // TODO determine if this really needs to be inverted
        right = new TalonGroup(new TalonSRX[] { new TalonSRX(fRightId), new TalonSRX(bRightId) }, true); 
        right.setNeutralMode(NeutralMode.Brake);

        leftLastEncTarget = 0;
        rightLastEncTarget = 0;
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        // TODO: odometry
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
    }

    /**
     * Drive the system with the given power scales.
     * 
     * @param yScale       Scale in the forward/backward direction, from 1 to -1.
     * @param angularScale Scale in the rotational direction, from 1 to -1,
     *                     clockwise to counterclockwise.
     */
    public void setDrivePowers(double yScale, double angularScale) {
        setDrivePowers(yScale, angularScale, true);
    }

    public void setDrivePowers(double yScale, double angularScale, boolean squareInput) {
        // Square the input if needed for finer control
        if (squareInput) {
            yScale = Math.copySign(yScale * yScale, yScale);
            angularScale = Math.copySign(angularScale * angularScale, angularScale);
        }

        double leftPower = yScale + angularScale;
        double rightPower = yScale - angularScale;

        double scale = 1.0 / Math.max(Math.abs(leftPower), Math.abs(rightPower));

        leftPower *= scale;
        rightPower *= scale;

        left.set(ControlMode.PercentOutput, leftPower);
        right.set(ControlMode.PercentOutput, rightPower);
    }

    //returns change in position of left encoder
    public int getLeftEncDelta() {
        return left.getQuadraturePos() - leftLastEncTarget;
    }

    //returns change in position of right encoder
    public int getRightEncDelta() {
        return right.getQuadraturePos() - rightLastEncTarget;
    }

    //remember encoder positions at the end of this command (so you can get
    //the delta in next command by subtracting these from the encoder positions)
    public void resetEncoders() {
        leftLastEncTarget = left.getQuadraturePos();
        rightLastEncTarget = right.getQuadraturePos();
    }

    public void followPathCommand() {

    }
}
