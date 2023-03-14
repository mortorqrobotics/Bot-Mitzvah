package org.team1515.botmitzvah.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import org.team1515.botmitzvah.Subsystems.Claw;

public class ClawIn extends CommandBase {
    private final Claw claw;

    public ClawIn(Claw claw) {
        this.claw = claw;
        addRequirements(claw);
    }

    @Override
    public void execute() {
        claw.intake();
    }

    @Override
    public void end(boolean interrupted) {
        claw.end();
    }
}