// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.team1515.botmitzvah;

import org.team1515.botmitzvah.RobotMap;
import org.team1515.botmitzvah.Utils.*;

import org.team1515.botmitzvah.Commands.*;
import org.team1515.botmitzvah.Commands.Autonomous.*;

import org.team1515.botmitzvah.Subsystems.*;
import org.team1515.botmitzvah.Controls;

import java.util.function.BooleanSupplier;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotContainer {
  public static XboxController mainController;
  public static XboxController secondController;

  public Drivetrain drivetrain;
  public Gyroscope gyro;
  public Claw claw;

  public RobotContainer() {
    mainController = new XboxController(0);
    secondController = new XboxController(1);

    drivetrain = new Drivetrain();
    gyro = new Gyroscope();
    claw = new Claw(RobotMap.CLAW_FORWARD_ID, RobotMap.CLAW_REVERSE_ID);

    configureBindings();
  }

  private void configureBindings() {

    // Controls.RESET_GYRO.onTrue(drivetrain::zeroGyroscope);

    Controls.ALIGN.onTrue(new AutoAlign(/* drivetrain */));

    if (claw.getExtended()) {
      Controls.GRAB.onTrue(new ClawOpen(claw));
    } else {
      Controls.GRAB.onTrue(new ClawClose(claw));
    }

  }

  public Command getAutonomousCommand() {
    return new AutoCommandScore(drivetrain);
  }
}
