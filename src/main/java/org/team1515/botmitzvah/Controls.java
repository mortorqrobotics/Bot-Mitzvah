package org.team1515.botmitzvah;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.button.Trigger;

public class Controls {
    // change botton maps later
    public static final Trigger RESET_GYRO = new Trigger(RobotContainer.mainController::getBackButton);
    public static final Trigger DRIVE_ROBOT_ORIENTED = new Trigger(RobotContainer.mainController::getLeftBumper);
    public static final Trigger CANCEL_ALL = new Trigger(RobotContainer.secondController::getBackButton);

    public static final Trigger MANUAL_PIVOT = new Trigger(RobotContainer.secondController::getRightStickButton);
    public static final Trigger MANUAL_FORWARD = new Trigger(Controls::getRightTriggerSecond);
    public static final Trigger MANUAL_BACKWARD = new Trigger(Controls::getLeftTriggerSecond);
    public static final Trigger GRAB = new Trigger(RobotContainer.secondController::getRightBumper);
    public static final Trigger RELEASE = new Trigger(RobotContainer.secondController::getLeftBumper);
    public static final Trigger USE_LIMIT = new Trigger(RobotContainer.secondController::getStartButton);

    // public static final Trigger B = new Trigger(RobotContainer.mainController::getYButton);

    // public static final Trigger ALIGN_TAG = new
    // Trigger(RobotContainer.mainController::getStartButton);
    // public static final Trigger ALIGN_LIGHT = new
    // Trigger(RobotContainer.mainController::getStartButton);
    public static final Trigger ZERO_ROBOT = new Trigger(RobotContainer.mainController::getStartButton);
    public static final Trigger FLIP_DRIVE_FORWARD = new Trigger(RobotContainer.mainController::getYButton);
    public static final Trigger PIVOT_LOW = new Trigger(RobotContainer.secondController::getAButton);
    public static final Trigger PIVOT_MID = new Trigger(RobotContainer.secondController::getXButton);
    public static final Trigger PIVOT_HIGH = new Trigger(RobotContainer.secondController::getYButton);
    public static final Trigger PIVOT_STOW = new Trigger(RobotContainer.secondController::getBButton);
    public static final Trigger RESET_WINCH = new Trigger(RobotContainer.secondController::getRightStickButton);
    public static DoubleSupplier MANUAL_PIVOT_VALUE = RobotContainer.secondController::getRightY;

    public static boolean getLeftTriggerMain() {
        return RobotContainer.mainController.getLeftTriggerAxis() >= 0.250;
    }

    public static boolean getRightTriggerMain() {
        return RobotContainer.mainController.getRightTriggerAxis() >= 0.250;
    }

    public static boolean getRightTriggerSecond() {
        return RobotContainer.secondController.getRightTriggerAxis() >= 0.250;
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

    public enum DPadButton {
        UP (0),
        RIGHT (90),
        DOWN (180),
        LEFT (270);

        private final int angle;
        DPadButton(int direction) {
            this.angle = direction;
        }

        public boolean getDPadButton() {
            return RobotContainer.secondController.getPOV() == angle;
        }
    }
}

// sticks for manual
// abx for set