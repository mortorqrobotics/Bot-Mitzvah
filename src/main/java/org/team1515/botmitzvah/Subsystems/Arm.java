package org.team1515.botmitzvah.Subsystems;

import org.team1515.botmitzvah.RobotMap;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
    private CANSparkMax arm;
    private boolean isOut;
    private DigitalInput outerSwitch;
    private DigitalInput middleSwitch;
    private DigitalInput innerSwitch;
    public static final double speed = 0.5;

    public Arm() {
        arm = new CANSparkMax(RobotMap.ARM_ID, MotorType.kBrushless);
        arm.restoreFactoryDefaults();

        isOut = false;
        outerSwitch = new DigitalInput(RobotMap.H_OUTER_SWITCH_ID);
        innerSwitch = new DigitalInput(RobotMap.H_INNER_SWITCH_ID);
        middleSwitch = new DigitalInput(RobotMap.H_MIDDLE_SWITCH_ID);
    }

    public void extend() {
        arm.set(speed);
    }

    public void retract() {
        arm.set(-speed);
    }

    public void end() {
        arm.set(0);
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
