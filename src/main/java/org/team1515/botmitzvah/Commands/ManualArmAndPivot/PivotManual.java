package org.team1515.botmitzvah.Commands.ManualArmAndPivot;

import java.util.function.DoubleSupplier;

import org.team1515.botmitzvah.RobotMap;
import org.team1515.botmitzvah.Subsystems.ArmPivot;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class PivotManual extends CommandBase {
  private final ArmPivot pivot;
  private DoubleSupplier speedSupplier;
  public static double upSpeed = RobotMap.ARM_PIVOT_UP_SPEED;
  public static double downSpeed = RobotMap.ARM_PIVOT_DOWN_SPEED;

  public PivotManual(ArmPivot pivot, DoubleSupplier speedSupplier) {
    this.pivot = pivot;
    this.speedSupplier = speedSupplier;

    addRequirements(pivot);
  }

  @Override
  public void execute() {
    double speed = -speedSupplier.getAsDouble();
    if(speed > 0) {
        speed *= upSpeed;
    }
    else {
        speed *= downSpeed;
    }
    pivot.setSpeed(speed);
  }

  @Override
  public void end(boolean interrupted) {
    pivot.end();
  }

  @Override
  public boolean isFinished() {
    return pivot.getOverLimit();
  }
}
