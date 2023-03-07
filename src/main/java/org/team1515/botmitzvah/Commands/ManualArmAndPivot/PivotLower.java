package org.team1515.botmitzvah.Commands.ManualArmAndPivot;

import org.team1515.botmitzvah.Subsystems.ArmPivot;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class PivotLower extends CommandBase {
  private final ArmPivot pivot;

  public PivotLower(ArmPivot pivot) {
    this.pivot = pivot;

    addRequirements(pivot);
  }

  @Override
  public void execute() {
    pivot.lower();
  }
}
