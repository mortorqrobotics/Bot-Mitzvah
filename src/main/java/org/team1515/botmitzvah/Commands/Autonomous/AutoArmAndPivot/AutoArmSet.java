package org.team1515.botmitzvah.Commands.Autonomous.AutoArmAndPivot;

import edu.wpi.first.wpilibj2.command.CommandBase;

import org.team1515.botmitzvah.Subsystems.Arm;

public class AutoArmSet extends CommandBase {
    private final Arm arm;
    private final double targetPosition;

    public AutoArmSet(Arm arm, double targetPosition) {
        this.arm = arm;
        this.targetPosition = targetPosition;
        addRequirements(arm);
    }

    @Override
    public void execute() {
        arm.setExtension(targetPosition);
    }

    @Override
    public void end(boolean interrupted) {
        arm.end();
    }

    @Override
    public boolean isFinished() {
        return arm.isAtSetPoint();
    }
}