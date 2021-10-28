package frc.robot.commands.swerve;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SwerveSubsystem;

public class DriveDistanceCommand extends CommandBase {
    
    private final SwerveSubsystem swerveSubsystem;
    private String direction;
    private float meters;
    
    //direction is either "forward" or "backward"
    public DriveDistanceCommand(SwerveSubsystem swerveSubsystem, String direction, float meters) {
        addRequirements(swerveSubsystem);
        this.swerveSubsystem = swerveSubsystem;
        this.direction = direction;
        this.meters = meters;
    }

    @Override
    public void execute() {
        //this would drive motors according to direction and meters
        swerveSubsystem.setDrivePowers(/* there would be params here */);
        
        //for now, just print what this command would do
        System.out.println("Driving " + this.meters + " meters " + this.direction);
    }
    
    @Override
    public boolean isFinished() {
        //for now, just return true
        return true;
    }
}
