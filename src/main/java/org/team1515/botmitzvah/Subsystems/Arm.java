package org.team1515.botmitzvah.Subsystems;

import org.team1515.botmitzvah.RobotMap;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
    private CANSparkMax arm;

    public static double speed = 0.25;

    public Arm() {
        arm = new CANSparkMax(RobotMap.ARM_ID, MotorType.kBrushless);
        arm.restoreFactoryDefaults();
        arm.setInverted(false);

        arm.setIdleMode(IdleMode.kBrake);
        arm.burnFlash();

        speed = RobotMap.ARM_SPEED;
    }

    public void extend() {
        arm.set(speed);
    }

    public void retract() {
        arm.set(-speed);
    }

    public boolean getOverExtended() {
        return false;
        //return arm.getEncoder().getPosition() > RobotMap.ARM_UPPER_LIMIT;
    }

    public boolean getUnderExtended() {
        return false;
        //return arm.getEncoder().getPosition() < RobotMap.ARM_LOWER_LIMIT;
    }

    public void end() {
        arm.set(0);
    }
}