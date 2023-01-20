package org.team1515.botmitzvah.Commands.Autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;

import org.team1515.botmitzvah.Subsystems.Arm;
import org.team1515.botmitzvah.Subsystems.Claw;

/** An example command that uses an example subsystem. */
public class AutoArmMid extends CommandBase {
    private final Arm arm;

    public AutoArmMid(Arm arm) {
        this.arm = arm;
        addRequirements(arm);
    }

    @Override
    public void execute() {
        if (arm.getIsOut()) {
            arm.retract();
        } else {
            arm.extend();
        }
    }

    @Override
    public void end(boolean interrupted) {
        arm.end();
    }

    @Override
    public boolean isFinished() {
        if (arm.getMiddle()) {
            if (!arm.getIsOut()) {
                arm.setIsOut(true);
            } else {
                arm.setIsOut(false);
            }
        }
        return arm.getMiddle();
    }
}