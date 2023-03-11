package org.team1515.botmitzvah.Commands.Autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;

import org.team1515.botmitzvah.Subsystems.Arm;
import org.team1515.botmitzvah.Subsystems.ArmPivot;
import org.team1515.botmitzvah.Utils.Utilities;

public class AutoPivotSet extends CommandBase {
    private final ArmPivot pivot;
    private final double angle;
    private final double pivotSpeed = 0.1;

    public AutoPivotSet(ArmPivot pivot, double angle) {
        this.pivot = pivot;
        this.angle = angle;
        addRequirements(pivot);
    }

    @Override
    public void execute() {
        if(pivot.getCancoderAngle() > angle) {
            pivot.setSpeed(pivotSpeed);
        }
        else {
            pivot.setSpeed(-pivotSpeed);
        }
    }

    @Override
    public void end(boolean interrupted) {
        pivot.end();
    }

    @Override
    public boolean isFinished() {
        return Utilities.epsilonEquals(pivot.getCancoderAngle(), angle, 3);
    }
}