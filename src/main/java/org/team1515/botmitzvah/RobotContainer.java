// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.team1515.botmitzvah;

import org.team1515.botmitzvah.Utils.*;

import org.team1515.botmitzvah.Commands.*;
import org.team1515.botmitzvah.Commands.Autonomous.*;
import org.team1515.botmitzvah.Commands.Autonomous.AutoArmAndPivot.*;
import org.team1515.botmitzvah.Commands.ManualArmAndPivot.*;
import org.team1515.botmitzvah.Subsystems.*;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class RobotContainer {
  public static XboxController mainController;
  public static XboxController secondController;

  private final SendableChooser<Integer> startingPose;

  public static Drivetrain drivetrain;
  public static Gyroscope gyro;
  public static TwoMeterSensor ir;
  public static PhotonVisionWrapper pvw;

  public static Claw claw;
  public static ArmPivot armPivot;
  public static Arm arm;

  public RobotContainer() {
    mainController = new XboxController(0);
    secondController = new XboxController(1);

    startingPose = new SendableChooser<>();
    startingPose.setDefaultOption("Charging Station", 1);
    startingPose.addOption("Close", 0);
    startingPose.addOption("Far", 2);

    gyro = new Gyroscope();
    ir = new TwoMeterSensor();
    pvw = new PhotonVisionWrapper();

    claw = new Claw();
    arm = new Arm();
    armPivot = new ArmPivot();

    configureBindings();
  }

  public void startup() {
    if (DriverStation.getAlliance() == Alliance.Red) {
      drivetrain = new Drivetrain(RobotMap.STARTING_RED[startingPose.getSelected()]);
    } else if (DriverStation.getAlliance() == Alliance.Blue) {
      drivetrain = new Drivetrain(RobotMap.STARTING_BLUE[startingPose.getSelected()]);
    } else {
      drivetrain = new Drivetrain(RobotMap.STARTING_BLUE[0]);
    }
  }

  private void configureBindings() {

    drivetrain.setDefaultCommand(
        new DefaultDriveCommand(drivetrain,
            () -> -modifyAxis(-mainController.getLeftY() * getRobotSpeed()),
            () -> -modifyAxis(-mainController.getLeftX() * getRobotSpeed()),
            () -> -modifyAxis(mainController.getRightX() * getRobotSpeed()),
            () -> Controls.DRIVE_ROBOT_ORIENTED.getAsBoolean()));

    Controls.RESET_GYRO.onTrue(new InstantCommand(() -> drivetrain.zeroGyro())); // drivetrain::zeroGyro not working

    // Controls.ALIGN_LIGHT.onTrue(Commands.sequence(new RotateToAngle(drivetrain,
    // new Rotation2d(0.0, 0.0)), new AlignLight(drivetrain)));
    // Controls.ALIGN_TAG.onTrue(Commands.sequence(new RotateToAngle(drivetrain, new
    // Rotation2d(0.0, 0.0)), new AlignTag(drivetrain)));
    Controls.ZERO_ROBOT.onTrue(new RotateToZero(drivetrain));

    Controls.GRAB.onTrue(new ClawClose(claw));
    Controls.RELEASE.onTrue(new ClawOpen(claw));

    // automized arm and elevator
    Controls.HIGH.onTrue(Commands.sequence(new AutoPivotSet(armPivot, RobotMap.ARM_PIVOT_BOTTOM_DEG), new AutoArmSet(arm, RobotMap.ARM_BOTTOM_POS)));
    Controls.MID.onTrue(Commands.sequence(new AutoPivotSet(armPivot, RobotMap.ARM_PIVOT_MIDDLE_DEG), new AutoArmSet(arm, RobotMap.ARM_MIDDLE_POS)));
    Controls.LOW.onTrue(Commands.sequence(new AutoPivotSet(armPivot, RobotMap.ARM_PIVOT_TOP_DEG), new AutoArmSet(arm, RobotMap.ARM_TOP_POS)));

    // manual arm and pivot
    Controls.MANUAL_UP.whileTrue(new PivotRaise(armPivot));
    Controls.MANUAL_DOWN.whileTrue(new PivotLower(armPivot));
    Controls.MANUAL_FORWARD.whileTrue(new ArmExtend(arm));
    Controls.MANUAL_BACKWARD.whileTrue(new ArmRetract(arm));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No auto command");
    // return new AutoCommandScore(drivetrain, arm, elevator, claw);
  }

  public static double getRobotSpeed() {
    return Controls.getLeftTrigger() ? 0.45 : 0.7;
    // return 0.7;
  }

  private static double modifyAxis(double value) {
    value = Utilities.deadband(value, 0.08);

    // Square the axis
    value = Math.copySign(value * value, value);

    return value;
  }

  public static void zeroArm() {
    new AutoArmIn(arm).schedule();
  }
}