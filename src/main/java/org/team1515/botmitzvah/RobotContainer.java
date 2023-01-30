// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.team1515.botmitzvah;

import org.team1515.botmitzvah.RobotMap;
import org.team1515.botmitzvah.Utils.*;

import org.team1515.botmitzvah.Commands.*;
import org.team1515.botmitzvah.Commands.Autonomous.*;
import org.team1515.botmitzvah.Commands.Autonomous.AutoArm.*;
import org.team1515.botmitzvah.Commands.Autonomous.AutoElevator.*;
import org.team1515.botmitzvah.Commands.ManualArmElevator.*;
import org.team1515.botmitzvah.Subsystems.*;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotContainer {
  public static XboxController mainController;
  public static XboxController secondController;

  public static Drivetrain drivetrain;
  public static Gyroscope gyro;
  public static PhotonVisionWrapper pvw;
  public static Claw claw;
  public static Elevator elevator;
  public static Arm arm;

  public RobotContainer() {
    mainController = new XboxController(0);
    secondController = new XboxController(1);

    gyro = new Gyroscope();
    pvw = new PhotonVisionWrapper();

    System.out.print(DriverStation.getAlliance().name());

    if (DriverStation.getAlliance().name().equals("kRed")) {
      drivetrain = new Drivetrain(RobotMap.STARTING_RED[DriverStation.getLocation() - 1]);
    } else if (DriverStation.getAlliance().name().equals("kRed")) {
      drivetrain = new Drivetrain(RobotMap.STARTING_BLUE[DriverStation.getLocation() - 1]);
    } else {
      drivetrain = new Drivetrain(RobotMap.STARTING_BLUE[0]);
    }
    claw = new Claw();
    arm = new Arm();
    elevator = new Elevator();

    configureBindings();
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
    Controls.ZERO_ROBOT.onTrue(new ZeroRobotTag(drivetrain));

    Controls.GRAB.onTrue(new ClawClose(claw));
    Controls.RELEASE.onTrue(new ClawOpen(claw));

    // automized arm and elevator
    Controls.HIGH.onTrue(Commands.parallel(new AutoArmOut(arm), new AutoElevatorUp(elevator)));
    Controls.MID.onTrue(Commands.parallel(new AutoArmMid(arm), new AutoElevatorDown(elevator)));
    Controls.LOW.onTrue(Commands.parallel(new AutoArmIn(arm), new AutoElevatorDown(elevator)));

    // manual arm and elevator
    Controls.MANUAL_UP.whileTrue(new Elevate(elevator));
    Controls.MANUAL_DOWN.whileTrue(new Lower(elevator));
    Controls.MANUAL_FORWARD.whileTrue(new Extend(arm));
    Controls.MANUAL_BACKWARD.whileTrue(new Retract(arm));

  }

  public Command getAutonomousCommand() {
    return new AutoCommandScore(drivetrain, arm, elevator, claw);
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
