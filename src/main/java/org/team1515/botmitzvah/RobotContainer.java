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
import org.team1515.botmitzvah.Subsystems.*;
import org.team1515.botmitzvah.Controls;

import java.util.function.BooleanSupplier;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
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

    // Controls.RESET_GYRO.onTrue(drivetrain::zeroGyroscope);

    // Controls.ALIGN.onTrue(new Align(/* drivetrain */));

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
    return new AutoCommandScore(drivetrain);
  }
}
