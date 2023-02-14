package org.team1515.botmitzvah.Commands.Autonomous.AutoArmAndPivot;

import edu.wpi.first.wpilibj2.command.CommandBase;

import org.team1515.botmitzvah.Subsystems.Arm;

public class AutoArmIn extends CommandBase {
    private final Arm arm;

    public AutoArmIn(Arm arm) {
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