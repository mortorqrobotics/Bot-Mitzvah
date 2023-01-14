package org.team1515.botmitzvah.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import org.team1515.botmitzvah.Subsystems.Claw;

/** An example command that uses an example subsystem. */
public class ClawOpen extends CommandBase {
    private final Claw claw;

    public ClawOpen(Claw claw) {
        this.claw = claw;
        addRequirements(claw);
    }

    @Override
    public void execute() {
        claw.retract();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}