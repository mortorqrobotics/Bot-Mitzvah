package org.team1515.botmitzvah.Commands.Autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;

import org.team1515.botmitzvah.Subsystems.Arm;
import org.team1515.botmitzvah.Utils.Utilities;

public class AutoArmSet extends CommandBase {
    private final Arm arm;
    private final double position;
    private final double armSpeed = 0.5;

    public AutoArmSet(Arm arm, double position) {
        this.arm = arm;
        this.position = position;
        addRequirements(arm);
    }

    @Override
    public void execute() {
        if(arm.getArmPosition() < position) {
            arm.setSpeed(armSpeed);
        }
        else {
            arm.setSpeed(-armSpeed);
        }
    }

    @Override
    public void end(boolean interrupted) {
        arm.end();
    }

    @Override
    public boolean isFinished() {
        return Utilities.epsilonEquals(arm.getArmPosition(), position, 1);
    }
}