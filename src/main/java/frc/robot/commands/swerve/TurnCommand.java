package frc.robot.commands.swerve;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SwerveSubsystem;

public class TurnCommand extends CommandBase {
    
    private final SwerveSubsystem swerveSubsystem;
    private String direction;
    private float degrees;
    
    //direction is either "left" or "right"
    public TurnCommand(SwerveSubsystem swerveSubsystem, String direction, float degrees) {
        this.swerveSubsystem = swerveSubsystem;
        this.direction = direction;
        this.degrees = degrees;
    }

    @Override
    public void execute() {
        //this would drive motors according to direction and degrees
        swerveSubsystem.setDrivePowers(/* there would be params here */);
        
        //for now, just print what this command would do
        System.out.println("Turning " + this.degrees + " degrees " + this.direction);
    }
    
    @Override
    public boolean isFinished() {
        //for now, just return true
        return true;
    }
}
