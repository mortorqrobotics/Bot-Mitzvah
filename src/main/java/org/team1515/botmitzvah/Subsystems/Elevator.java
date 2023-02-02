package org.team1515.botmitzvah.Subsystems;

import org.team1515.botmitzvah.RobotMap;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Elevator extends SubsystemBase {
    private CANSparkMax elevator;
    private boolean isOut;
    private DigitalInput lowerSwitch;
    private DigitalInput middleSwitch;
    private DigitalInput upperSwitch;
    public static final double speed = 0.5;

    public Elevator() {
        elevator = new CANSparkMax(RobotMap.ELEVATOR_ID, MotorType.kBrushless);
        elevator.restoreFactoryDefaults();

        isOut = false;
        lowerSwitch = new DigitalInput(RobotMap.V_LOWER_SWITCH_ID);
        upperSwitch = new DigitalInput(RobotMap.V_UPPER_SWITCH_ID);
        middleSwitch = new DigitalInput(RobotMap.V_MIDDLE_SWITCH_ID);
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

    public boolean getLower() {
        return lowerSwitch.get();
    }

    public boolean getMiddle() {
        return middleSwitch.get();
    }

    public boolean getIsOut() {
        return isOut;
    }

    public boolean setIsOut(boolean out) {
        return isOut = out;
    }
}