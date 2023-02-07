// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.team1515.botmitzvah;

import org.team1515.botmitzvah.Utils.*;

import com.team364.swervelib.util.CTREConfigs;

import edu.wpi.first.wpilibj.TimedRobot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
  public static CTREConfigs config;

  private static RobotContainer robotContainer;

  private static Command m_autonomousCommand;

  public static Limelight limelight;
  public static AprilTag apriltag;

  @Override
  public void robotInit() {
    config = new CTREConfigs();

    robotContainer = new RobotContainer();
    limelight = new Limelight();
    apriltag = new AprilTag();
    RobotContainer.zeroArm();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
    if(RobotContainer.arm.getInner()){ // just incase command failsafe doesn't work
        RobotContainer.arm.end();
    }
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void disabledExit() {
  }

  @Override
  public void autonomousInit() {
    robotContainer.startup();
    m_autonomousCommand = robotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void autonomousExit() {
  }

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void teleopExit() {
  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {
  }

  @Override
  public void testExit() {
  }
}