package frc.robot.commands.swerve;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SwerveSubsystem;

public class DriveTimeCommand extends CommandBase {
    
    private final SwerveSubsystem swerveSubsystem;
    private String direction;
    private float seconds;

    //direction is either "forward" or "backward"
    public DriveTimeCommand(SwerveSubsystem swerveSubsystem, String direction, float seconds) {
        addRequirements(swerveSubsystem);
        this.swerveSubsystem = swerveSubsystem;
        this.direction = direction;
        this.seconds = seconds;
    }

    @Override
    public void execute() {
        //this would drive motors according to direction and seconds
        swerveSubsystem.setDrivePowers(/* there would be params here */);
        
        //for now, just print what this command would do
        System.out.println("Driving " + this.seconds + " seconds " + this.direction);
    }
    
    @Override
    public boolean isFinished() {
        //for now, just return true
        return true;
    }
}
