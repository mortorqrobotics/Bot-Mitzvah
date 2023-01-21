package org.team1515.botmitzvah.Commands.Autonomous.AutoArm;

import edu.wpi.first.wpilibj2.command.CommandBase;

import org.team1515.botmitzvah.Subsystems.Arm;

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
        if (arm.getMiddle()) {
            if (arm.getIsOut()) {
                arm.setIsOut(false);
            } else {
                arm.setIsOut(true);
            }
        }
        arm.end();
    }

    @Override
    public boolean isFinished() {
        return arm.getMiddle();
    }
}