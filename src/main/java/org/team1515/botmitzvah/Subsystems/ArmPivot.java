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
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.trajectory.TrapezoidProfile.State;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmPivot extends SubsystemBase {
    private CANSparkMax pivotMotor;
    private CANCoder canCoder;
    private ArmPivotMap pivotMap;

    private final TrapezoidProfile.Constraints m_constraints = new TrapezoidProfile.Constraints(1.75, 0.75);
    public ProfiledPIDController controller = new ProfiledPIDController(0, 0, 0, m_constraints);

    public boolean usePid = false;
    public double armSpeed = 0.05; // degrees per 20 ms

    public ArmPivot() {
        pivotMotor = new CANSparkMax(RobotMap.ARM_PIVOT_ID, MotorType.kBrushless);

        canCoder = new CANCoder(RobotMap.ARM_PIVOT_CANCODER_ID);
        CANCoderConfiguration pivotCanCoderConfig = new CANCoderConfiguration();
        pivotCanCoderConfig.absoluteSensorRange = AbsoluteSensorRange.Signed_PlusMinus180;
        pivotCanCoderConfig.sensorDirection = false; // double check this
        pivotCanCoderConfig.initializationStrategy = SensorInitializationStrategy.BootToAbsolutePosition;
        pivotCanCoderConfig.sensorTimeBase = SensorTimeBase.PerSecond;
        pivotCanCoderConfig.magnetOffsetDegrees = -RobotMap.ARM_PIVOT_OFFSET;
        canCoder.configAllSettings(pivotCanCoderConfig);

        controller.reset(getAngle());

        pivotMotor.setIdleMode(IdleMode.kBrake);
        pivotMotor.burnFlash();

        pivotMap = new ArmPivotMap();
    }

    /**
     * @return double angle of the arm in degrees based on cancoder angle   
     */
    public double getAngle() {
        return canCoder.getAbsolutePosition();
    }

    /**
     * Sets the angle setpoint of the pivot
     * @param angle in degrees
     */
    public void setAngle(double angle) {
        angle = MathUtil.clamp(angle, RobotMap.ARM_PIVOT_LOWER_LIMIT, RobotMap.ARM_PIVOT_UPPER_LIMIT);
        controller.setGoal(new State(angle, 0));
    }

    public void raise() {
        setAngle(getAngle() + 0.05);
    }

    public void lower() {
        setAngle(getAngle() - 0.05);
    }

    public boolean isOverRotated() {
        return getAngle() > RobotMap.ARM_PIVOT_UPPER_LIMIT;
    }

    public boolean isUnderRotated() {
        return getAngle() < RobotMap.ARM_PIVOT_LOWER_LIMIT;
    }

    /**
     * Calculates feedforward based on the current pivot angle and arm extension
     * @returns arbitrary feedforward in volts
     */
    public double calculateFeedForward(double targetVelocity) {
        return pivotMap.calculate(getAngle(), targetVelocity, RobotContainer.arm.extension);
    }

    @Override
    public void periodic() {
        if(usePid) {
            if(!isOverRotated()) return;
            double volts = controller.calculate(getAngle()) + calculateFeedForward(controller.getSetpoint().velocity); // double check this logic
            if(isUnderRotated()) {
                volts = Math.max(volts, 0);
            }
            pivotMotor.setVoltage(volts);
        }
    }
}