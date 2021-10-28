package frc.robot.commands.swerve;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SwerveSubsystem;

public class DriveTimeCommand extends CommandBase {
    
    private final SwerveSubsystem swerveSubsystem;

    //direction: 1=forwards, -1=backwards
    public DriveTimeCommand(SwerveSubsystem swerveSubsystem, String direction, float seconds) {
        this.swerveSubsystem = swerveSubsystem;
        addRequirements(swerveSubsystem);
        System.out.println("Driving " + seconds + " seconds " + direction);
    }

    @Override
    public void initialize() {
        //this would drive motors according to direction and seconds
        swerveSubsystem.setDrivePowers(/* there would be params here */);
    }
    
    @Override
    public boolean isFinished() {
        return true;
    }
}
