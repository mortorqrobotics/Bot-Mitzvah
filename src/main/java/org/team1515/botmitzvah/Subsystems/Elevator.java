package org.team1515.botmitzvah.Subsystems;

import org.team1515.botmitzvah.RobotMap;
import org.team1515.botmitzvah.Utils.Utilities;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Elevator extends SubsystemBase {
    private CANSparkMax elevator;
    private RelativeEncoder encoder;

    private SparkMaxPIDController controller;
    private double setPoint;

    private boolean isOut;
    private DigitalInput upperSwitch;
    private DigitalInput middleSwitch;
    private DigitalInput lowerSwitch;

    public static final double speed = 0.5;

    public Elevator() {
        elevator = new CANSparkMax(RobotMap.ELEVATOR_ID, MotorType.kBrushless);
        elevator.restoreFactoryDefaults();

        encoder = elevator.getEncoder();

        controller = elevator.getPIDController();
        controller.setP(RobotMap.ELEVATOR_KP);
        controller.setI(RobotMap.ELEVATOR_KI);
        controller.setD(RobotMap.ELEVATOR_KD);
        controller.setFF(RobotMap.ELEVATOR_KFF);

        elevator.setIdleMode(IdleMode.kBrake);
        elevator.burnFlash();

        isOut = false;
        upperSwitch = new DigitalInput(RobotMap.V_UPPER_SWITCH_ID);
        middleSwitch = new DigitalInput(RobotMap.V_MIDDLE_SWITCH_ID);
        lowerSwitch = new DigitalInput(RobotMap.V_LOWER_SWITCH_ID);
    }

    public void extend() {
        elevator.set(speed);
    }

    public void retract() {
        elevator.set(-speed);
    }

    public void end() {
        elevator.set(0);
    }

    public boolean getUpper() {
        return upperSwitch.get();
    }

    public boolean getMiddle() {
        return middleSwitch.get();
    }

    public boolean getLower() {
        return lowerSwitch.get();
    }

    public boolean getIsOut() {
        return isOut;
    }

    public boolean setIsOut(boolean out) {
        return isOut = out;
    }

    public void goToUpper() {
        controller.setReference(RobotMap.ELEVATOR_UPPER_POS, ControlType.kPosition);
        setPoint = RobotMap.ELEVATOR_UPPER_POS;
    }

    public void goToMiddle() {
        controller.setReference(RobotMap.ELEVATOR_MIDDLE_POS, ControlType.kPosition);
        setPoint = RobotMap.ELEVATOR_MIDDLE_POS;
    }

    public void goToLower() {
        controller.setReference(RobotMap.ELEVATOR_LOWER_POS, ControlType.kPosition);
        setPoint = RobotMap.ELEVATOR_LOWER_POS;

    }

    public boolean isAtSetPoint() {
        return Utilities.deadband(setPoint - encoder.getPosition(), RobotMap.ELEVATOR_TOLERANCE) == 0;
    }
}