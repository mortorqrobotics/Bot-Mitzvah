package org.team1515.botmitzvah.Commands.Autonomous.AutoArm;

import edu.wpi.first.wpilibj2.command.CommandBase;

import org.team1515.botmitzvah.Subsystems.Arm;

/**
 * autonomously moves robot arm to inner position using a limit switch
 */

public class AutoArmIn extends CommandBase {
    private final Arm arm;

    public AutoArmIn(Arm arm) {
        this.arm = arm;
        addRequirements(arm);
    }

    @Override
    public void execute() {
        arm.goToInner();
    }

    @Override
    public void end(boolean interrupted) {
        if (arm.getInner()) {
            arm.setIsOut(false);
            arm.zeroEncoder();
        }
        arm.end();
    }

    @Override
    public boolean isFinished() {
        return arm.getInner();
    }
}