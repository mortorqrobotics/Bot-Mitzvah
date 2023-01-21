// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.team1515.botmitzvah;

import org.team1515.botmitzvah.RobotMap;
import org.team1515.botmitzvah.Utils.*;

import org.team1515.botmitzvah.Commands.*;
import org.team1515.botmitzvah.Commands.Autonomous.*;

import org.team1515.botmitzvah.Subsystems.*;

import com.team364.swervelib.util.SwerveConstants;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotContainer {
  public static XboxController mainController;
  public static XboxController secondController;

  public static Drivetrain drivetrain;
  public static Gyroscope gyro;
  public static Claw claw;
  public static Elevator elevator;
  public static Arm arm;

  public RobotContainer() {
    mainController = new XboxController(0);
    secondController = new XboxController(1);

    gyro = new Gyroscope();

    drivetrain = new Drivetrain();

    claw = new Claw(RobotMap.CLAW_FORWARD_ID, RobotMap.CLAW_REVERSE_ID);

    configureBindings();
  }

  private void configureBindings() {

    drivetrain.setDefaultCommand(
        new DefaultDriveCommand(drivetrain,
            () -> -modifyAxis(-mainController.getLeftY() * getRobotSpeed()) * SwerveConstants.Swerve.maxSpeed,
            () -> -modifyAxis(-mainController.getLeftX() * getRobotSpeed()) * SwerveConstants.Swerve.maxSpeed,
            () -> -modifyAxis(mainController.getRightX() * getRobotSpeed()) * SwerveConstants.Swerve.maxAngularVelocity,
            () -> Controls.DRIVE_ROBOT_ORIENTED.getAsBoolean()));

    Controls.RESET_GYRO.onTrue(new InstantCommand(() -> drivetrain.zeroGyro())); // drivetrain::zeroGyro not working

    Controls.ALIGN_LIGHT.onTrue(new AlignLight(drivetrain));
    Controls.ALIGN_TAG.onTrue(new AlignLight(drivetrain));

    Controls.GRAB.onTrue(new ClawClose(claw));
    Controls.RELEASE.onTrue(new ClawOpen(claw));

    Controls.MANUAL_UP.onTrue(new Elevate(elevator));
    Controls.MANUAL_DOWN.onTrue(new Lower(elevator));
    Controls.MANUAL_FORWARD.onTrue(new Extend(arm));
    Controls.MANUAL_BACKWARD.onTrue(new Retract(arm));

  }

  public Command getAutonomousCommand() {
    return new AutoCommandScore(drivetrain);
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

}
