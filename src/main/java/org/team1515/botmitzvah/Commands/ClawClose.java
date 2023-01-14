package org.team1515.botmitzvah.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import org.team1515.botmitzvah.Subsystems.Claw;

/** An example command that uses an example subsystem. */
public class ClawClose extends CommandBase {
    private final Claw claw;

    public ClawClose(Claw claw) {
        this.claw = claw;
        addRequirements(claw);
    }

    @Override
    public void execute() {
        claw.extend();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}