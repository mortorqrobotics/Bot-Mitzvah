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
    public static final Trigger MANUAL_UP = new Trigger(Controls::getLSUp);
    public static final Trigger MANUAL_DOWN = new Trigger(Controls::getLSDown);
    public static final Trigger MANUAL_FORWARD = new Trigger(Controls::getRSUp);
    public static final Trigger MANUAL_BACK = new Trigger(Controls::getRSDown);
    
    public static boolean getRightTrigger() {
        return RobotContainer.mainController.getRightTriggerAxis() >= 0.250;
    }

    public static boolean getLeftTrigger() {
        return RobotContainer.mainController.getLeftTriggerAxis() >= 0.250;
    }

    public static boolean secondRightStickTrigger() {
        return RobotContainer.secondController.getRightTriggerAxis() >= 0.250;
    }

    public static boolean getLSUp(){
        return RobotContainer.secondController.getLeftY()>0;
    }
    public static boolean getLSDown(){
        return RobotContainer.secondController.getLeftY()<0;
    }
    public static boolean getRSUp(){
        return RobotContainer.secondController.getLeftY()>0;
    }
    public static boolean getRSDown(){
        return RobotContainer.secondController.getLeftY()<0;
    }
}

//sticks for manual
//abx for set