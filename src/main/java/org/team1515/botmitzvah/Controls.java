package org.team1515.botmitzvah;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.button.Trigger;

public class Controls {
    // change botton maps later

    public static final Trigger GRAB = new Trigger(RobotContainer.secondController::getRightBumper);
    public static final Trigger RELEASE = new Trigger(RobotContainer.secondController::getLeftBumper);
    public static final Trigger ALIGN = new Trigger(RobotContainer.mainController::getStartButton); // will limelight and apriltag overlap?

    public static final Trigger RESET_GYRO = new Trigger(RobotContainer.mainController::getBackButton);
    public static final BooleanSupplier DRIVE_ROBOT_ORIENTED = () -> RobotContainer.mainController.getLeftBumper();
    public static final Trigger HIGH = new Trigger(RobotContainer.secondController::getXButton);
    public static final Trigger MID = new Trigger(RobotContainer.secondController::getBButton);
    public static final Trigger LOW = new Trigger(RobotContainer.secondController::getAButton);
    public static final Trigger MANUAL_UP = new Trigger(Controls::getLeftStickUp);
    public static final Trigger MANUAL_DOWN = new Trigger(Controls::getLeftStickDown);
    public static final Trigger MANUAL_FORWARD = new Trigger(Controls::getRightStickUp);
    public static final Trigger MANUAL_BACKWARD = new Trigger(Controls::getRightStickDown);
    
    public static boolean getRightTrigger() {
        return RobotContainer.mainController.getRightTriggerAxis() >= 0.250;
    }

    public static boolean getLeftTrigger() {
        return RobotContainer.mainController.getLeftTriggerAxis() >= 0.250;
    }

    public static boolean secondRightStickTrigger() {
        return RobotContainer.secondController.getRightTriggerAxis() >= 0.250;
    }

    public static boolean getLeftStickUp() {
        return RobotContainer.secondController.getLeftY()>0;
    }
    public static boolean getLeftStickDown() {
        return RobotContainer.secondController.getLeftY()<0;
    }
    public static boolean getRightStickUp() {
        return RobotContainer.secondController.getLeftY()>0;
    }
    public static boolean getRightStickDown() {
        return RobotContainer.secondController.getLeftY()<0;
    }
}

//sticks for manual
//abx for set