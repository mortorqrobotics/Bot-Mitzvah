package org.team1515.botmitzvah.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import org.team1515.botmitzvah.Subsystems.Claw;

public class ClawOut extends CommandBase {
    private final Claw claw;

    public ClawOut(Claw claw) {
        this.claw = claw;
        addRequirements(claw);
    }

    @Override
    public void execute() {
        claw.outtake();
    }

    @Override
    public void end(boolean interrupted) {
        claw.end();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}