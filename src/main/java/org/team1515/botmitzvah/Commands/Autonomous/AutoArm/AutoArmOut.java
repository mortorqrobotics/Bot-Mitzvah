package org.team1515.botmitzvah.Commands.Autonomous.AutoArm;

import edu.wpi.first.wpilibj2.command.CommandBase;

import org.team1515.botmitzvah.Subsystems.Arm;

/**
 * autonomously moves robot arm to outer position using a limit switch
 */

public class AutoArmOut extends CommandBase {
    private final Arm arm;

    public AutoArmOut(Arm arm) {
        this.arm = arm;
        addRequirements(arm);
    }

    @Override
    public void execute() {
        arm.extend();
    }

    @Override
    public void end(boolean interrupted) {
        if (arm.getOuter()) {
            arm.setIsOut(true);
        }
        arm.end();
    }

    @Override
    public boolean isFinished() {
        return arm.getOuter();
    }
}