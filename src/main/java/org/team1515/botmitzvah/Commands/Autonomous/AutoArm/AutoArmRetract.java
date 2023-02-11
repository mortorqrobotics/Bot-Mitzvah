package org.team1515.botmitzvah.Commands.Autonomous.AutoArm;

import edu.wpi.first.wpilibj2.command.CommandBase;

import org.team1515.botmitzvah.Subsystems.Arm;

/**
 * autonomously moves robot arm to middle position using a limit switch
 * changes state of isOut variable when touching limit switch
 * if arm is past the middle switch, retract, otherwise expand
 */

public class AutoArmRetract extends CommandBase {
    private final Arm arm;

    public AutoArmRetract(Arm arm) {
        this.arm = arm;
        addRequirements(arm);
    }

    @Override
    public void execute() {
        arm.retract();
    }

    @Override
    public void end(boolean interrupted) {
        arm.end();
    }

    @Override
    public boolean isFinished() {
        return arm.getRetracted();
    }
}