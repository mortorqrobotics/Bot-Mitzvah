package org.team1515.botmitzvah.Commands.ManualArmAndPivot;

import edu.wpi.first.wpilibj2.command.CommandBase;

import org.team1515.botmitzvah.Subsystems.Arm;

public class ArmRetract extends CommandBase {
    private final Arm arm;

    public ArmRetract(Arm arm) {
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
}