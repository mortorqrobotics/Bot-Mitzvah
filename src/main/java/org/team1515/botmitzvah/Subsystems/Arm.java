package org.team1515.botmitzvah.Subsystems;

import org.team1515.botmitzvah.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
    private TalonFX arm;
    private boolean isOut;
    private DigitalInput outerSwitch;
    private DigitalInput middleSwitch;
    private DigitalInput innerSwitch;
    public static final double speed = 0.5;

    public Arm() {
        arm = new TalonFX(RobotMap.ARM_ID);
        isOut = false;
        outerSwitch = new DigitalInput(RobotMap.OUTER_SWITCH_ID);
        innerSwitch = new DigitalInput(RobotMap.INNER_SWITCH_ID);
        middleSwitch = new DigitalInput(RobotMap.MIDDLE_SWITCH_ID);
    }

    public void extend() {
        arm.set(ControlMode.PercentOutput, speed);
    }

    public void retract() {
        arm.set(ControlMode.PercentOutput, -speed);
    }

    public void end() {
        arm.set(ControlMode.PercentOutput, 0);
    }

    public boolean getOuter() {
        return outerSwitch.get();
    }

    public boolean getInner() {
        return innerSwitch.get();
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
