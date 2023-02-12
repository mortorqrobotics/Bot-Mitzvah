package org.team1515.botmitzvah.Subsystems;

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

import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;

public class ArmPivot {
    private CANSparkMax pivotMotor;
    private RelativeEncoder encoder;
    private CANCoder canCoder;
    private SparkMaxPIDController controller;
    private ArmFeedforward feedforward;

    private double setPoint;
    private double speed = RobotMap.ARM_PIVOT_SPEED;

    public ArmPivot() {
        pivotMotor = new CANSparkMax(RobotMap.ARM_PIVOT_ID, MotorType.kBrushless);
        encoder = pivotMotor.getEncoder();
        controller = pivotMotor.getPIDController();

        canCoder = new CANCoder(RobotMap.ARM_PIVOT_CANCODER_ID);
        CANCoderConfiguration pivotCanCoderConfig = new CANCoderConfiguration();
        pivotCanCoderConfig.absoluteSensorRange = AbsoluteSensorRange.Unsigned_0_to_360;
        pivotCanCoderConfig.sensorDirection = false; // double check this
        pivotCanCoderConfig.initializationStrategy = SensorInitializationStrategy.BootToAbsolutePosition;
        pivotCanCoderConfig.sensorTimeBase = SensorTimeBase.PerSecond;
        canCoder.configAllSettings(pivotCanCoderConfig);

        controller.setP(RobotMap.ARM_PIVOT_KP);
        controller.setI(RobotMap.ARM_PIVOT_KI);
        controller.setD(RobotMap.ARM_PIVOT_KD);

        pivotMotor.setIdleMode(IdleMode.kBrake);
        pivotMotor.burnFlash();

        feedforward = new ArmFeedforward(RobotMap.ARM_PIVOT_KS, RobotMap.ARM_PIVOT_KG, RobotMap.ARM_PIVOT_KV);

        resetToAbsolute();
    }

    public Rotation2d getCancoderAngle() {
        return Rotation2d.fromDegrees(canCoder.getAbsolutePosition());
    }

    public void resetToAbsolute() {
        double absolutePosition = Utilities.degreesToRev(getCancoderAngle().getDegrees() - RobotMap.ARM_PIVOT_OFFSET, RobotMap.ARM_PIVOT_GEAR_RATIO);
        encoder.setPosition(absolutePosition);
    }

    /**
     * Sets pivot to specified angle with pid
     * @param angle in degrees
     */
    public void setPivotAngle(double angle) {
        double position = Utilities.degreesToRev(angle, RobotMap.ARM_PIVOT_GEAR_RATIO);
        controller.setReference(position, ControlType.kVelocity, 0, feedforward.calculate(Units.degreesToRadians(angle), 0), ArbFFUnits.kVoltage); // TODO: Consider SMART MOTION
    }

    public double getPositionRev() {
        return encoder.getPosition();
    }

    /**
     * @return double angle of the arm in degrees based on internal encoder
     */
    public double getAngle() {
        return Utilities.revToDegrees(getPositionRev(), RobotMap.ARM_PIVOT_GEAR_RATIO);
    }

    public boolean isAtSetPoint() {
        return Utilities.deadband(setPoint - encoder.getPosition(), RobotMap.ARM_TOLERANCE) == 0;
    }

    public void rotateClockwise() {
        pivotMotor.set(speed);
    }

    public void rotateCounterClockwise() {
        pivotMotor.set(-speed);
    }

    /**
     * @return boolean true if not over or under rotated
     */
    public boolean isInBounds() {
        return getAngle() > RobotMap.ARM_PIVOT_MIN_DEGREES && getAngle() < RobotMap.ARM_PIVOT_MAX_DEGREES;
    }
}
