package org.team1515.botmitzvah.Commands.Autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;

import org.team1515.botmitzvah.Subsystems.ArmPivot;
import org.team1515.botmitzvah.Utils.Utilities;

public class AutoPivotSet extends CommandBase {
    private final ArmPivot pivot;
    private final double angle;
    private final double upSpeed = 0.30;
    private final double downSpeed = -0.25;

    public AutoPivotSet(ArmPivot pivot, double angle) {
        this.pivot = pivot;
        this.angle = angle;
        addRequirements(pivot);
    }

    @Override
    public void execute() {
        if(pivot.getCancoderAngle() < angle) {
            pivot.setSpeed(upSpeed);
        }
        else {
            pivot.setSpeed(downSpeed);
        }
    }

    @Override
    public void end(boolean interrupted) {
        pivot.end();
    }

    @Override
    public boolean isFinished() {
        return Utilities.epsilonEquals(pivot.getCancoderAngle(), angle, 1);
    }
}