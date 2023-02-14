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

    private DigitalInput retractedSwitch;

    public static double speed;

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

        retractedSwitch = new DigitalInput(RobotMap.ARM_RETRACT_SWITCH);

        speed = RobotMap.ARM_SPEED;
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

    public boolean getRetracted() {
        return retractedSwitch.get();
    }
    
    public void setExtension(double position) {
        controller.setReference(position, ControlType.kPosition);
        setPoint = position;
    }

    public boolean isAtSetPoint() {
        return Utilities.deadband(setPoint - encoder.getPosition(), RobotMap.ARM_TOLERANCE) == 0;
    }

    /**
     * @return boolean true if the arm is not over or under extended
     */
    public boolean isInBounds() {
        return !getRetracted() && (getPosition() > RobotMap.ARM_MAX_POS);
    }

    public void zeroEncoder() {
        encoder.setPosition(0.0);
    }

    public double getPosition() {
        return encoder.getPosition();
    }
}