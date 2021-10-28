// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SwerveSubsystem extends SubsystemBase {
    public SwerveSubsystem() {
        CommandScheduler.getInstance().registerSubsystem(this);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
    }

    //there would be params in this method (e.g. the direction and RPM to set each motor to)
    public void setDrivePowers() {}

    //return whether or not the current command is finished
    public boolean getFinished() {
        //this is where it would check if the robot has turned the desired amount,
        //moved the desired distance, etc.

        //for the sake of testing, just returns true (as if every command finished instantly)
        return true;
    }
}
