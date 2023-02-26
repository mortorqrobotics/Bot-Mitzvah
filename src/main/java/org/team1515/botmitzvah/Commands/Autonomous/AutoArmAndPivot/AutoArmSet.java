package org.team1515.botmitzvah.Commands.Autonomous.AutoArmAndPivot;

import edu.wpi.first.wpilibj2.command.CommandBase;

import org.team1515.botmitzvah.Subsystems.Arm;
import org.team1515.botmitzvah.Subsystems.Arm.Extension;

public class AutoArmSet extends CommandBase {
    private final Arm arm;
    private final Extension targetExtension;

    public AutoArmSet(Arm arm, Extension extension) {
        this.arm = arm;
        this.targetExtension = extension;
        addRequirements(arm);
    }

    @Override
    public void initialize() {
        arm.setExtension(targetExtension);
    }

    @Override
    public void execute() {
        if(arm.isPastSetpoint()) {
            arm.retract();
        }
        else {
            arm.extend();
        }
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