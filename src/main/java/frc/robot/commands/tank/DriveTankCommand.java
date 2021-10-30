// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.tank;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.tank.TankSubsystem;

/** An example command that uses an example subsystem. */
public class DriveTankCommand extends CommandBase {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    private final TankSubsystem tankSubsystem;

    //target change in position (number of ticks) for each encoder; command will
    //be finished when these targets are reached
    private int leftEncTargetDelta;
    private int rightEncTargetDelta;

    private double seconds;
    private double startTime;

    //for mode, 0=turning, 1=driving seconds, 2=driving meters
    //value for driving: positive=forward and negative=backward
    //value for turning: positive=clockwise/right and negative=counterclockwise/left
    public DriveTankCommand(TankSubsystem tankSubsystem, int mode, double value) {
        super();

        this.tankSubsystem = tankSubsystem;
        addRequirements(tankSubsystem);

        //if seconds is 0, it means this command is not driving based on time
        this.seconds = 0;
        this.startTime = 0;
        
        switch (mode) {
            //getting the polarity correct for targets (keeping in mind that clockwise
            //for the right side motors is the opposite as clockwise on the left)
            case 0:
                this.leftEncTargetDelta = (int) Math.round(-value/Constants.ENCODER_TICKS_TO_DEGREES);
                this.rightEncTargetDelta = (int) Math.round(-value/Constants.ENCODER_TICKS_TO_DEGREES);
                break;
            case 1:
                //this case is when it is driving based on time, so set seconds value
                this.seconds = value;
                this.leftEncTargetDelta = (int) Math.round(-value/Constants.ENCODER_TICKS_TO_METERS);
                this.rightEncTargetDelta = (int) Math.round(value/Constants.ENCODER_TICKS_TO_METERS);
                break;
            case 2:
                this.leftEncTargetDelta = (int) Math.round(-value/Constants.ENCODER_TICKS_TO_METERS);
                this.rightEncTargetDelta = (int) Math.round(value/Constants.ENCODER_TICKS_TO_METERS);
                break;
        }
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void initialize() {
        //drive forwards
        if (leftEncTargetDelta < 0 && rightEncTargetDelta > 0) tankSubsystem.setDrivePowers(0.5, 0, true);
        //drive backwards
        else if (leftEncTargetDelta > 0 && rightEncTargetDelta < 0) tankSubsystem.setDrivePowers(-0.5, 0, true);
        //turn right
        else if (leftEncTargetDelta < 0 && rightEncTargetDelta < 0) tankSubsystem.setDrivePowers(0, 0.5, true);
        //turn left
        else tankSubsystem.setDrivePowers(0, -0.5, true);

        //set start time for timed commands
        if (this.seconds == 0) this.startTime = System.currentTimeMillis();

        //print message to check that it is doing stuff
        System.out.println("left encoder target = " + leftEncTargetDelta
            + "; right encoder target = " + rightEncTargetDelta);
    }

    @Override
    public boolean isFinished() {
        //for commands that don't use timer
        if (this.seconds == 0) {
            //i.e. if the distance that the encoders have moved is equal or
            //further than the target, it is done doing the command
            //absolute values simplify dealing with negative numbers
            if (Math.abs(tankSubsystem.getLeftEncDelta()) >= Math.abs(leftEncTargetDelta) &&
                Math.abs(tankSubsystem.getRightEncDelta()) >= Math.abs(rightEncTargetDelta)) return true;
            else return false;
        //for commands that are timed (e.g. drive forward 1.92 seconds)
        } else {
            if (System.currentTimeMillis() >= this.startTime + 1000*this.seconds) return true;
            else return false;
        }
    }

    public void end() {
        tankSubsystem.setDrivePowers(0, 0, false);
        tankSubsystem.resetEncoders();
    }
}
