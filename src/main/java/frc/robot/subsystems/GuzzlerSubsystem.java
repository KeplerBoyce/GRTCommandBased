// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class GuzzlerSubsystem extends SubsystemBase {

    private int numBlocks = 0;//how many blocks have been taken in

    public GuzzlerSubsystem() {
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

    //do whatever the process is for the guzzler mech taking a block in
    public void intakeBlock() {
        //for now, just pretend that the mech takes in the block and increase numBlocks
        numBlocks++;
    }

    //get how many blocks are currently stored
    public int getNumBlocks() {
        return numBlocks;
    }
}
