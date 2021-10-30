// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.guzzler;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.guzzler.GuzzlerSubsystem;

/** An example command that uses an example subsystem. */
public class GuzzlerIntakeCommand extends CommandBase {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    private final GuzzlerSubsystem guzzler;

    public GuzzlerIntakeCommand(GuzzlerSubsystem guzzler) {
        this.guzzler = guzzler;
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void initialize() {
        guzzler.intakeBlock();
        int num = guzzler.getNumBlocks();
        System.out.println("taking block in; " + num + " blocks in storage");
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    public void end() {}
}
