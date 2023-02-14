package org.team1515.botmitzvah.Commands.ManualArmAndPivot;

import edu.wpi.first.wpilibj2.command.CommandBase;

import org.team1515.botmitzvah.Subsystems.Arm;

public class ArmExtend extends CommandBase {
    private final Arm arm;

    public ArmExtend(Arm arm) {
        this.arm = arm;
        addRequirements(arm);
    }

    @Override
    public void execute() {
        arm.extend();
    }

    @Override
    public void end(boolean interrupted) {
        arm.end();
    }

    @Override
    public boolean isFinished() {
        return !arm.isInBounds();
    }
}