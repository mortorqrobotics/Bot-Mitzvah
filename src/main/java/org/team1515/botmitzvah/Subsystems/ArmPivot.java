package org.team1515.botmitzvah.Subsystems;

import org.team1515.botmitzvah.RobotContainer;
import org.team1515.botmitzvah.RobotMap;
import org.team1515.botmitzvah.Utils.Utilities;

import com.ctre.phoenix.sensors.AbsoluteSensorRange;
import com.ctre.phoenix.sensors.CANCoder;
import com.ctre.phoenix.sensors.CANCoderConfiguration;
import com.ctre.phoenix.sensors.SensorInitializationStrategy;
import com.ctre.phoenix.sensors.SensorTimeBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxPIDController.ArbFFUnits;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmPivot extends SubsystemBase {
    private CANSparkMax pivotMotor;
    private CANCoder encoder;

    public static double speed = RobotMap.ARM_PIVOT_SPEED;

    public ArmPivot() {
        pivotMotor = new CANSparkMax(RobotMap.ARM_PIVOT_ID, MotorType.kBrushless);
        pivotMotor.setInverted(true);

        encoder = new CANCoder(RobotMap.ARM_PIVOT_CANCODER_ID); // replace
        CANCoderConfiguration pivotCanCoderConfig = new CANCoderConfiguration();
        pivotCanCoderConfig.absoluteSensorRange = AbsoluteSensorRange.Signed_PlusMinus180;
        pivotCanCoderConfig.sensorDirection = false; // double check this
        pivotCanCoderConfig.initializationStrategy = SensorInitializationStrategy.BootToAbsolutePosition;
        pivotCanCoderConfig.sensorTimeBase = SensorTimeBase.PerSecond;
        pivotCanCoderConfig.magnetOffsetDegrees = -RobotMap.ARM_PIVOT_OFFSET;
        encoder.configAllSettings(pivotCanCoderConfig);

        pivotMotor.setIdleMode(IdleMode.kBrake);
        pivotMotor.burnFlash();
    }

    /**
     * @return double angle of the cancoder in degrees
     */
    public double getCancoderAngle() {
        return encoder.getAbsolutePosition();
    }

    public void raise() {
        pivotMotor.set(speed);
    }

    public void setSpeed(double speed) {
        pivotMotor.set(speed);
    }

    public void lower() {
        pivotMotor.set(-speed);
    }
    public boolean getOverLimit() {
        return false;
        //return encoder.getPosition() > RobotMap.ARM_PIVOT_UPPER_LIMIT;
    }

    public boolean getUnderLimit() {
        return false;
        //return encoder.getPosition() < RobotMap.ARM_PIVOT_LOWER_LIMIT;
    }

    public void end() {
        pivotMotor.set(0);
    }
}