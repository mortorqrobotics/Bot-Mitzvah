package org.team1515.botmitzvah;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.button.Trigger;

public class Controls {
    // change botton maps later
    public static final Trigger GRAB = new Trigger(RobotContainer.mainController::getXButton);
    public static final Trigger ALIGN = new Trigger(RobotContainer.mainController::getStartButton); // will limelight
                                                                                                    // and apriltag
                                                                                                    // overlap?
    public static final Trigger RESET_GYRO = new Trigger(RobotContainer.mainController::getBackButton);
    public static final BooleanSupplier DRIVE_ROBOT_ORIENTED = () -> RobotContainer.mainController.getLeftBumper();

    // Climber Buttons
    // public static final Button EXPAND_VERTICAL = new
    // Button(OI.mainStick::getRightBumper);
    // public static final Button RETRACT_VERTICAL = new
    // Button(Controls::getRightTrigger);
    // public static final Button EXPAND_DIAGONAL = new
    // Button(OI.mainStick::getYButton);
    // public static final Button RETRACT_DIAGONAL = new
    // Button(OI.mainStick::getXButton);

    // public static final Button MANUAL_CLIMBER_L = new
    // Button(OI.mainStick::getBButton);
    // public static final Button MANUAL_CLIMBER_R = new
    // Button(OI.mainStick::getAButton);

    // public static final Button LEFT_DPAD = new
    // Button(DPadButton.LEFT::getDPadButton);
    // public static final Button RIGHT_DPAD = new
    // Button(DPadButton.RIGHT::getDPadButton);
    // public static final Button UP_DPAD = new
    // Button(DPadButton.UP::getDPadButton);
    // public static final Button DOWN_DPAD = new
    // Button(DPadButton.DOWN::getDPadButton);

    public static boolean getRightTrigger() {
        return RobotContainer.mainController.getRightTriggerAxis() >= 0.250;
    }

    public static boolean getLeftTrigger() {
        return RobotContainer.mainController.getLeftTriggerAxis() >= 0.250;
    }

    public static boolean secondRightStickTrigger() {
        return RobotContainer.secondController.getRightTriggerAxis() >= 0.250;
    }

    // public enum DPadButton {
    // UP(0),
    // RIGHT(90),
    // DOWN(180),
    // LEFT(270);

    // private final int angle;

    // DPadButton(int direction) {
    // this.angle = direction;
    // }

    // public boolean getDPadButton() {
    // return RobotContainer.mainController.getPOV() == angle;
    // }
    // }
}