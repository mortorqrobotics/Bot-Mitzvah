package org.team1515.botmitzvah.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import org.team1515.botmitzvah.Subsystems.Arm;

/** An example command that uses an example subsystem. */
public class Extend extends CommandBase {
    private final Arm arm;

    public Extend(Arm arm) {
        this.arm = arm;
        addRequirements(arm);
    }

    @Override
    public void execute() {
        arm.extend();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}