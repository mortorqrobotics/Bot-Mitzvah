package org.team1515.botmitzvah.Subsystems;

import org.team1515.botmitzvah.RobotMap;
import org.team1515.botmitzvah.Utils.Utilities;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
    private CANSparkMax arm;
    private RelativeEncoder encoder; 

    public static double speed = 0.25;

    public static enum Extension {
        Extended(RobotMap.ARM_EXTENDED_POS),
        Retracted(RobotMap.ARM_RETRACTED_POS);

        public double position;

        private Extension(double position) {
            this.position = position;
        }
    }

    public Extension extension;

    public Arm() {
        arm = new CANSparkMax(RobotMap.ARM_ID, MotorType.kBrushless);
        arm.restoreFactoryDefaults();
        arm.setInverted(false);

        encoder = arm.getEncoder();

        arm.setIdleMode(IdleMode.kBrake);
        arm.burnFlash();

        speed = RobotMap.ARM_SPEED;
    }

    /*
     * Return true if position is greater than set point position
     */
    public boolean isPastSetpoint() {
        return getPosition() > extension.position;
    }

    public void extend() {
        double percentSpeed = !isOverExtended() ? speed : 0;
        arm.set(percentSpeed);
    }

    public void retract() {
        double percentSpeed = !isUnderExtended() ? -speed : 0;
        arm.set(percentSpeed);
    }

    public boolean isOverExtended() {
        return getPosition() > RobotMap.ARM_UPPER_LIMIT;
    }

    public void setExtension(Extension extension) {
        this.extension = extension;
    }

    public boolean isUnderExtended() {
        return getPosition() < RobotMap.ARM_LOWER_LIMIT;
    }

    public boolean isAtSetPoint() {
        return Utilities.epsilonEquals(extension.position, getPosition(), RobotMap.ARM_TOLERANCE);
    }

    public void setEncoder(double position) { // use at start of match
        encoder.setPosition(position);
    }

    public double getPosition() {
        return encoder.getPosition();
    }

    public void end() {
        arm.set(0);
    }
}