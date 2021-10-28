package frc.robot.commands.swerve;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SwerveSubsystem;

public class DriveDistanceCommand extends CommandBase {
    
    private final SwerveSubsystem swerveSubsystem;
    
    public DriveDistanceCommand(SwerveSubsystem swerveSubsystem, String direction, float meters) {
        this.swerveSubsystem = swerveSubsystem;
        addRequirements(swerveSubsystem);
        System.out.println("Driving " + meters + " meters " + direction);
    }

    @Override
    public void initialize() {
        //this would drive motors according to direction and meters
        swerveSubsystem.setDrivePowers(/* there would be params here */);
    }
    
    @Override
    public boolean isFinished() {
        return true;
    }
}
