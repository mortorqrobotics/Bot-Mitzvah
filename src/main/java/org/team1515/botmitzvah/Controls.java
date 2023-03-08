package org.team1515.botmitzvah;

import edu.wpi.first.wpilibj2.command.button.Trigger;

public class Controls {
    // change botton maps later

    public static final Trigger GRAB = new Trigger(RobotContainer.secondController::getRightBumper);
    public static final Trigger RELEASE = new Trigger(Controls::getRightTriggerSecond);

    public static final Trigger SWITCH_HOLDING_STATE = new Trigger(Controls::getLeftTriggerSecond);

    public static final Trigger RESET_GYRO = new Trigger(RobotContainer.mainController::getBackButton);
    public static final Trigger DRIVE_ROBOT_ORIENTED = new Trigger(RobotContainer.mainController::getLeftBumper);

    public static final Trigger MANUAL_UP = new Trigger(RobotContainer.secondController::getYButton);
    public static final Trigger MANUAL_DOWN = new Trigger(RobotContainer.secondController::getAButton);
    public static final Trigger MANUAL_FORWARD = new Trigger(RobotContainer.secondController::getXButton);
    public static final Trigger MANUAL_BACKWARD = new Trigger(RobotContainer.secondController::getBButton);

    public static final Trigger AUTO_PIVOT_LOW = new Trigger(RobotContainer.secondController::getAButton);
    public static final Trigger AUTO_PIVOT_MID = new Trigger(RobotContainer.secondController::getAButton);
    public static final Trigger AUTO_PIVOT_HIGH = new Trigger(RobotContainer.secondController::getAButton);


    // public static final Trigger ALIGN_TAG = new
    // Trigger(RobotContainer.mainController::getStartButton);
    // public static final Trigger ALIGN_LIGHT = new
    // Trigger(RobotContainer.mainController::getStartButton);
    public static final Trigger ZERO_ROBOT = new Trigger(RobotContainer.mainController::getStartButton);
    public static final Trigger DRIVE = new Trigger(RobotContainer.mainController::getXButton);

    public static boolean getRightTriggerMain() {
        return RobotContainer.mainController.getRightTriggerAxis() >= 0.250;
    }

    public static boolean getRightTriggerSecond() {
        return RobotContainer.secondController.getRightTriggerAxis() >= 0.250;
    }

    public static boolean getLeftTrigger() {
        return RobotContainer.mainController.getLeftTriggerAxis() >= 0.250;
    }

    public static boolean getLeftTriggerSecond() {
        return RobotContainer.secondController.getLeftTriggerAxis() >= 0.250;
    }

    public static boolean secondRightStickTrigger() {
        return RobotContainer.secondController.getRightTriggerAxis() >= 0.250;
    }

    public static boolean getLeftStickUp() {
        return RobotContainer.secondController.getLeftY() > 0;
    }

    public static boolean getLeftStickDown() {
        return RobotContainer.secondController.getLeftY() < 0;
    }

    public static boolean getRightStickUp() {
        return RobotContainer.secondController.getLeftY() > 0;
    }

    public static boolean getRightStickDown() {
        return RobotContainer.secondController.getLeftY() < 0;
    }
}

// sticks for manual
// abx for set