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

public class Arm extends SubsystemBase {
    private CANSparkMax arm;
    private RelativeEncoder encoder;

    private SparkMaxPIDController controller;
    private double setPoint;

    private boolean isOut;
    private DigitalInput outerSwitch;
    private DigitalInput middleSwitch;
    private DigitalInput innerSwitch;

    public static final double speed = 0.5;

    public Arm() {
        arm = new CANSparkMax(RobotMap.ARM_ID, MotorType.kBrushless);
        arm.restoreFactoryDefaults();

        encoder = arm.getEncoder();

        controller = arm.getPIDController();
        controller.setP(RobotMap.ARM_KP);
        controller.setI(RobotMap.ARM_KI);
        controller.setD(RobotMap.ARM_KD);
        controller.setFF(RobotMap.ARM_KFF);

        arm.setIdleMode(IdleMode.kBrake);
        arm.burnFlash();

        isOut = false;
        outerSwitch = new DigitalInput(RobotMap.H_OUTER_SWITCH_ID);
        middleSwitch = new DigitalInput(RobotMap.H_MIDDLE_SWITCH_ID);
        innerSwitch = new DigitalInput(RobotMap.H_INNER_SWITCH_ID);
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

    public boolean getMiddle() {
        return middleSwitch.get();
    }

    public boolean getInner() {
        return innerSwitch.get();
    }

    public boolean getIsOut() {
        return isOut;
    }

    public boolean setIsOut(boolean out) {
        return isOut = out;
    }

    public void goToOuter() {
        controller.setReference(RobotMap.ARM_OUTER_POS, ControlType.kPosition);
        setPoint = RobotMap.ARM_OUTER_POS;
    }

    public void goToMiddle() {
        controller.setReference(RobotMap.ARM_MIDDLE_POS, ControlType.kPosition);
        setPoint = RobotMap.ARM_MIDDLE_POS;
    }

    public void goToInner() {
        controller.setReference(RobotMap.ARM_INNER_POS, ControlType.kPosition);
        setPoint = RobotMap.ARM_INNER_POS;
    }

    public boolean isAtSetPoint() {
        return Utilities.deadband(setPoint - encoder.getPosition(), RobotMap.ARM_TOLERANCE) == 0;
    }

    public void setZero() {
        retract();
    }

    @Override
    public void periodic() {
        if(outerSwitch.get()){
            encoder.setPosition(RobotMap.ARM_OUTER_POS);
        }
        if(middleSwitch.get()){
            encoder.setPosition(RobotMap.ARM_MIDDLE_POS);
        }
        if(innerSwitch.get()){
            encoder.setPosition(RobotMap.ARM_INNER_POS);
        }
    }
}