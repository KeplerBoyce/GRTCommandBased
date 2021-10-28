package frc.robot.commands.guzzler;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.GuzzlerSubsystem;

public class GuzzlerIntakeCommand extends CommandBase {
    
    private final GuzzlerSubsystem guzzlerSubsystem;
    
    public GuzzlerIntakeCommand(GuzzlerSubsystem guzzlerSubsystem) {
        this.guzzlerSubsystem = guzzlerSubsystem;
        addRequirements(guzzlerSubsystem);
    }

    @Override
    public void initialize() {
        //call method for guzzler mech to take block in
        guzzlerSubsystem.intakeBlock();
        
        //print message
        System.out.println("Guzzler taking block in");
    }
    
    @Override
    public boolean isFinished() {
        //for now, just return true
        return true;
    }
}
