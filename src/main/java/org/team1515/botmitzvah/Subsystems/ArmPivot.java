package org.team1515.botmitzvah.Subsystems;

import org.team1515.botmitzvah.RobotContainer;
import org.team1515.botmitzvah.RobotMap;
import org.team1515.botmitzvah.Utils.ArmPivotMap;
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
    private RelativeEncoder encoder;
    private CANCoder canCoder;
    private SparkMaxPIDController controller;
    private ArmPivotMap pivotMap;

    private double setPoint;
    public boolean usePid = false;

    public ArmPivot() {
        pivotMotor = new CANSparkMax(RobotMap.ARM_PIVOT_ID, MotorType.kBrushless);
        encoder = pivotMotor.getEncoder();
        controller = pivotMotor.getPIDController();

        canCoder = new CANCoder(RobotMap.ARM_PIVOT_CANCODER_ID);
        CANCoderConfiguration pivotCanCoderConfig = new CANCoderConfiguration();
        pivotCanCoderConfig.absoluteSensorRange = AbsoluteSensorRange.Signed_PlusMinus180;
        pivotCanCoderConfig.sensorDirection = false; // double check this
        pivotCanCoderConfig.initializationStrategy = SensorInitializationStrategy.BootToAbsolutePosition;
        pivotCanCoderConfig.sensorTimeBase = SensorTimeBase.PerSecond;
        canCoder.configAllSettings(pivotCanCoderConfig);

        controller.setP(RobotMap.ARM_PIVOT_KP);
        controller.setI(RobotMap.ARM_PIVOT_KI);
        controller.setD(RobotMap.ARM_PIVOT_KD);

        pivotMotor.setIdleMode(IdleMode.kBrake);
        pivotMotor.burnFlash();

        pivotMap = new ArmPivotMap();

        resetToAbsolute();
        setAngle(getAngle());
    }

    public Rotation2d getCancoderAngle() {
        return Rotation2d.fromDegrees(canCoder.getAbsolutePosition());
    }

    public void resetToAbsolute() {
        double absolutePosition = Utilities.degreesToRev(getAngle(), RobotMap.ARM_PIVOT_GEAR_RATIO);
        encoder.setPosition(absolutePosition);
    }

    /**
     * Sets the angle setpoint of the pivot
     * @param angle in degrees
     */
    public void setAngle(double angle) {
        angle = MathUtil.clamp(angle, RobotMap.ARM_PIVOT_MIN_DEG, RobotMap.ARM_PIVOT_MAX_DEG);
        double position = Utilities.degreesToRev(angle, RobotMap.ARM_PIVOT_GEAR_RATIO);
        setPoint = position;
    }

    public double getPositionRev() {
        return encoder.getPosition();
    }

    /**
     * @return double angle of the arm in degrees based on cancoder angle   
     */
    public double getAngle() {
        return getCancoderAngle().getDegrees() - RobotMap.ARM_PIVOT_OFFSET;
    }

    public boolean isAtSetPoint() {
        return Utilities.epsilonEquals(setPoint, encoder.getPosition(), RobotMap.ARM_TOLERANCE);
    }

    public void raise() {
        setPoint += 0.1;
    }

    public void lower() {
        setPoint -= 0.1;
    }

    public void end() {
        pivotMotor.set(0);
    }

    /**
     * @return boolean true if not over or under rotated
     */
    public boolean isInBounds() {
        return getAngle() > RobotMap.ARM_PIVOT_MIN_DEG && getAngle() < RobotMap.ARM_PIVOT_MAX_DEG;
    }

    /**
     * Calculates feedforward based on the current pivot angle and arm extension
     * @returns arbitrary feedforward in volts
     */
    public double calculateFeedForward() {
        return pivotMap.get(getAngle(), RobotContainer.arm.extension);
    }

    @Override
    public void periodic() {
        if(usePid) {
            controller.setReference(setPoint, ControlType.kPosition, 0, calculateFeedForward(), ArbFFUnits.kVoltage);
        }
    }
}