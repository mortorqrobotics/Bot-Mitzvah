// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.team1515.botmitzvah;

import org.team1515.botmitzvah.Commands.ClawIn;
import org.team1515.botmitzvah.Commands.ClawOut;
import org.team1515.botmitzvah.Commands.DefaultDriveCommand;
import org.team1515.botmitzvah.Commands.RotateToZero;
import org.team1515.botmitzvah.Commands.Autonomous.AutoPivotSet;
import org.team1515.botmitzvah.Commands.Autonomous.AutoCommands.AutoCommandLeave;
import org.team1515.botmitzvah.Commands.Autonomous.AutoCommands.AutoCommandScore;
import org.team1515.botmitzvah.Commands.Autonomous.AutoCommands.OldAutoCommandBalance;
import org.team1515.botmitzvah.Commands.Autonomous.CalcCommands.driveCircle;
import org.team1515.botmitzvah.Commands.Autonomous.CalcCommands.driveLine;
import org.team1515.botmitzvah.Commands.ManualArmAndPivot.ArmExtend;
import org.team1515.botmitzvah.Commands.ManualArmAndPivot.ArmRetract;
import org.team1515.botmitzvah.Commands.ManualArmAndPivot.PivotManual;
import org.team1515.botmitzvah.Subsystems.Arm;
import org.team1515.botmitzvah.Subsystems.ArmPivot;
import org.team1515.botmitzvah.Subsystems.Claw;
import org.team1515.botmitzvah.Subsystems.Drivetrain;
import org.team1515.botmitzvah.Utils.Gyroscope;
import org.team1515.botmitzvah.Utils.Utilities;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class RobotContainer {
  public static XboxController mainController;
  public static XboxController secondController;

  public static Drivetrain drivetrain;
  public static Gyroscope gyro;

  public static Claw claw;
  public static ArmPivot armPivot;
  public static Arm arm;


  private static SendableChooser<Command> autonomousChooser = new SendableChooser<>();

  public RobotContainer() {
    mainController = new XboxController(0);
    secondController = new XboxController(1);

    gyro = new Gyroscope();

    drivetrain = new Drivetrain(new Pose2d());
    claw = new Claw();
    arm = new Arm();
    armPivot = new ArmPivot();

    autonomousChooser.setDefaultOption("None", Commands.print("No auto command"));
    autonomousChooser.addOption("Leave", new AutoCommandLeave(drivetrain));
    // autonomousChooser.addOption("GyroBalance", new AutoCommandBalance(drivetrain));
    autonomousChooser.addOption("Balance", new OldAutoCommandBalance(drivetrain));
    autonomousChooser.addOption("Score", new AutoCommandScore(drivetrain, armPivot, claw, arm));
    // autonomousChooser.addOption("Andrew", new AutoCommandBalance(drivetrain));
    SmartDashboard.putData("Auto Choices", autonomousChooser);

    configureBindings();
  }

  private void configureBindings() {
    drivetrain.setDefaultCommand(
        new DefaultDriveCommand(drivetrain,
            () -> -modifyAxis(-mainController.getLeftY() * getRobotSpeed()),
            () -> -modifyAxis(-mainController.getLeftX() * getRobotSpeed()),
            () -> modifyAxis(mainController.getRightX() * getRobotSpeed()),
            () -> Controls.DRIVE_ROBOT_ORIENTED.getAsBoolean()));

    Controls.RESET_GYRO.onTrue(new InstantCommand(() -> drivetrain.zeroGyro()));

    Controls.CANCEL_ALL.onTrue(new InstantCommand(() -> CommandScheduler.getInstance().cancelAll()));

    Controls.ZERO_ROBOT.onTrue(new RotateToZero(drivetrain));
    Controls.FLIP_DRIVE_FORWARD.onTrue(new InstantCommand(() -> drivetrain.flipGyro()));

    Controls.GRAB.whileTrue(new ClawIn(claw));
    Controls.RELEASE.whileTrue(new ClawOut(claw));
    Controls.USE_LIMIT.onTrue(new InstantCommand(() -> arm.useLimits = !arm.useLimits));
    // Controls.RESET_WINCH.onTrue(new InstantCommand(()->arm.resetArmPosition(0)));

    Controls.MANUAL_PIVOT.whileTrue(new PivotManual(armPivot, Controls.MANUAL_PIVOT_VALUE));
    Controls.MANUAL_FORWARD.whileTrue(new ArmExtend(arm));
    Controls.MANUAL_BACKWARD.whileTrue(new ArmRetract(arm));

    // Controls.B.whileTrue(new AutoBalance(drivetrain));

    Controls.PIVOT_LOW.onTrue(new AutoPivotSet(armPivot, -38));
    Controls.PIVOT_MID.onTrue(new AutoPivotSet(armPivot, 3));
    Controls.PIVOT_HIGH.onTrue(new AutoPivotSet(armPivot, 15));
    Controls.PIVOT_STOW.onTrue(new AutoPivotSet(armPivot, RobotMap.ARM_PIVOT_STOWED_ANGLE));
  }
  
  public Command getAutonomousCommand() {
    // return autonomousChooser.getSelected();
    // return new driveLine(drivetrain, -0.5, 0.5, 0, 3);
    return new SequentialCommandGroup(new driveLine(drivetrain, Math.PI*(2), -1, 1, 0,1), new driveLine(drivetrain,Math.PI*(2), 1,0,0,1), new driveLine(drivetrain,Math.PI*(2), 0,1,0,1), new driveCircle(drivetrain, Math.PI*(-2), 0.75, 0, 2*Math.PI, false));
    // return new DriveDist(drivetrain, 1);
  }

  public static double getRobotSpeed() {
    return Controls.getLeftTriggerMain() ? 0.45 : 0.75;
    // return 0.7;
  }

  private static double modifyAxis(double value) {
    value = Utilities.deadband(value, 0.08);

    // Square the axis
    value = Math.copySign(value * value, value);

    return value;
  }
}