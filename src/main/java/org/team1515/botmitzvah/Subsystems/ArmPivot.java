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

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmPivot extends SubsystemBase {
    private CANSparkMax pivotMotor;
    private RelativeEncoder encoder;
    private CANCoder canCoder;
    private SparkMaxPIDController controller;
    private ArmFeedforward feedforward;

    private double setPoint;
    private double speed = RobotMap.ARM_PIVOT_SPEED;
    private TrapezoidProfile motionProfile;
    private TrapezoidProfile.Constraints constraits;

    private Timer timer;

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

        TrapezoidProfile.Constraints constraits = new TrapezoidProfile.Constraints(RobotMap.ARM_PIVOT_MAX_VELOCITY, RobotMap.ARM_PIVOT_MAX_ACCELERATION);
        TrapezoidProfile.State initalGoal = new TrapezoidProfile.State(0, 0);
        motionProfile = new TrapezoidProfile(constraits, initalGoal);

        timer = new Timer();

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
     * setTrapezoidGoal must be called before calling this method.
     * @param angle in degrees
     */
    public void setAngle(double angle) {
        angle = MathUtil.clamp(angle, RobotMap.ARM_PIVOT_MIN_DEG, RobotMap.ARM_PIVOT_MAX_DEG);
        double position = Utilities.degreesToRev(angle, RobotMap.ARM_PIVOT_GEAR_RATIO);
        setPoint = position;
        TrapezoidProfile.State state = motionProfile.calculate(timer.get());
        controller.setReference(setPoint, ControlType.kVelocity, 0, feedforward.calculate(state.position, state.velocity), ArbFFUnits.kVoltage);
    }

    public double getPositionRev() {
        return encoder.getPosition();
    }

    /**
     * Sets trapezoid motion profile goal. 
     * @param goal target goal in degrees
     */
    public void setTrapezoidGoal(double goal) {
        timer.restart();
        TrapezoidProfile.State goalState = new TrapezoidProfile.State(Units.degreesToRadians(goal), 0);
        TrapezoidProfile.State initalState = new TrapezoidProfile.State(Units.degreesToRadians(getAngle()), 0);
        motionProfile = new TrapezoidProfile(constraits, goalState, initalState);
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

    public void raise() {
        pivotMotor.set(speed);
    }

    public void lower() {
        pivotMotor.set(-speed);
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
}
