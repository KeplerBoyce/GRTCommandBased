package frc.robot.commands.swerve;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SwerveSubsystem;

public class TurnCommand extends CommandBase {
    
    private final SwerveSubsystem swerveSubsystem;
    
    public TurnCommand(SwerveSubsystem swerveSubsystem, String direction, float degrees) {
        this.swerveSubsystem = swerveSubsystem;
        addRequirements(swerveSubsystem);
        //for now, just print what this command would do
        System.out.println("Turning " + degrees + " degrees " + direction);
    }

    @Override
    public void initialize() {
        //this would drive motors according to direction and degrees
        swerveSubsystem.setDrivePowers(/* there would be params here */);
    }
    
    @Override
    public boolean isFinished() {
        //check if swerve subsystem is done with this command
        return swerveSubsystem.getFinished();
    }
}
