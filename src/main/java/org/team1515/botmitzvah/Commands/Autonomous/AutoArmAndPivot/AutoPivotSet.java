package org.team1515.botmitzvah.Commands.Autonomous.AutoArmAndPivot;

import org.team1515.botmitzvah.Subsystems.ArmPivot;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoPivotSet extends CommandBase {
    private final ArmPivot pivot;
    private final double targetAngle;

    public AutoPivotSet(ArmPivot pivot, double targetAngle) {
        this.pivot = pivot;
        this.targetAngle = targetAngle;
        
        addRequirements(pivot);
    }

    @Override
    public void initialize() {
        this.pivot.setAngle(this.targetAngle);
    }

    @Override
    public void execute() {
        pivot.pivotPeriodic();
    }

    @Override
    public void end(boolean interrupted) {
        pivot.end();
    }

    @Override
    public boolean isFinished() {
        return !pivot.isInBounds();
    }
}
