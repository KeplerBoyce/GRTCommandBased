// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.TextCommandGroup;
import frc.robot.subsystems.GuzzlerSubsystem;
import frc.robot.subsystems.SwerveSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    // The robot's subsystems and commands are defined here
    private final SwerveSubsystem swerveSubsystem = new SwerveSubsystem();
    private final GuzzlerSubsystem guzzlerSubsystem = new GuzzlerSubsystem();

    private final TextCommandGroup textCommandGroup = new TextCommandGroup(swerveSubsystem, guzzlerSubsystem);

    public RobotContainer() {}

    public Command getTextCommandGroup() {
        return textCommandGroup;
    }
}
