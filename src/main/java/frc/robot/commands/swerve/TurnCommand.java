package frc.robot.commands.swerve;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SwerveSubsystem;

public class TurnCommand extends CommandBase {
    
    private final SwerveSubsystem swerveSubsystem;
    
    public TurnCommand(SwerveSubsystem swerveSubsystem, String direction, float degrees) {
        this.swerveSubsystem = swerveSubsystem;
        addRequirements(swerveSubsystem);
        System.out.println("Turning " + degrees + " degrees " + direction);
    }

    @Override
    public void initialize() {
        //this would drive motors according to direction and degrees
        swerveSubsystem.setDrivePowers(/* there would be params here */);
    }
    
    @Override
    public boolean isFinished() {
        return true;
    }
}
