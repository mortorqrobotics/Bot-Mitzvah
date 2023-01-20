package org.team1515.botmitzvah.Commands.Autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;

import org.team1515.botmitzvah.Subsystems.Arm;
import org.team1515.botmitzvah.Subsystems.Claw;

/** An example command that uses an example subsystem. */
public class AutoArmIn extends CommandBase {
    private final Arm arm;

    public AutoArmIn(Arm arm) {
        this.arm = arm;
        addRequirements(arm);
    }

    @Override
    public void execute() {
        // if (!arm.getInner()) {
        arm.retract();
        // }
    }

    @Override
    public void end(boolean interrupted) {
        arm.end();
    }

    @Override
    public boolean isFinished() {
        return arm.getInner();
    }
}