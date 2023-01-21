package org.team1515.botmitzvah.Subsystems;

import org.team1515.botmitzvah.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Elevator extends SubsystemBase {
    private TalonFX elevator;
    private boolean isOut;
    private DigitalInput lowerSwitch;
    private DigitalInput middleSwitch;
    private DigitalInput upperSwitch;
    public static final double speed = 0.5;

    public Elevator() {
        elevator = new TalonFX(RobotMap.ELEVATOR_ID);
        isOut = false;
        lowerSwitch = new DigitalInput(RobotMap.LOWER_SWITCH_ID);
        upperSwitch = new DigitalInput(RobotMap.UPPER_SWITCH_ID);
        middleSwitch = new DigitalInput(RobotMap.V_MIDDLE_SWITCH_ID);
    }

    public void extend() {
        elevator.set(ControlMode.PercentOutput, speed);
    }

    public void retract() {
        elevator.set(ControlMode.PercentOutput, -speed);
    }

    public void end() {
        elevator.set(ControlMode.PercentOutput, 0);
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
