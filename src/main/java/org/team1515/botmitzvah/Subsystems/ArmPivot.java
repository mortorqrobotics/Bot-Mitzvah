package org.team1515.botmitzvah.Subsystems;

import org.team1515.botmitzvah.RobotContainer;
import org.team1515.botmitzvah.RobotMap;
import org.team1515.botmitzvah.Subsystems.Arm.Extension;
import org.team1515.botmitzvah.Utils.ArmPivotMap;

import com.ctre.phoenix.sensors.AbsoluteSensorRange;
import com.ctre.phoenix.sensors.CANCoder;
import com.ctre.phoenix.sensors.CANCoderConfiguration;
import com.ctre.phoenix.sensors.SensorInitializationStrategy;
import com.ctre.phoenix.sensors.SensorTimeBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.trajectory.TrapezoidProfile.State;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmPivot extends SubsystemBase {
    private CANSparkMax pivotMotor;
    private CANCoder canCoder;
    private ArmPivotMap pivotMap;

    private final TrapezoidProfile.Constraints m_constraints = new TrapezoidProfile.Constraints(1.75, 0.75);
    public ProfiledPIDController controller = new ProfiledPIDController(0.08, 0.00  , 0, m_constraints);

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

        // controller.reset(getAngle());
        controller.setGoal(getAngle());

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
        double lowerLimit = RobotContainer.arm.extension == Extension.Extended ? RobotMap.ARM_PIVOT_EXTENDED_LOWER_LIMIT : RobotMap.ARM_PIVOT_RETRACTED_LOWER_LIMIT;
        angle = MathUtil.clamp(angle, lowerLimit, RobotMap.ARM_PIVOT_UPPER_LIMIT);
        controller.setGoal(new State(angle, 0));
    }

    public void raise() {
        // setAngle(getAngle() + 0.05);
        pivotMotor.set(-0.1);
    }

    public void lower() {
        // setAngle(getAngle() - 0.05);
        pivotMotor.set(-0.1);
    }

    public boolean isOverRotated() {
        return getAngle() > RobotMap.ARM_PIVOT_UPPER_LIMIT;
    }

    public boolean isUnderRotated() {
        double lowerLimit = RobotContainer.arm.extension == Extension.Extended ? RobotMap.ARM_PIVOT_EXTENDED_LOWER_LIMIT : RobotMap.ARM_PIVOT_RETRACTED_LOWER_LIMIT;
        return getAngle() < lowerLimit;
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
        SmartDashboard.putBoolean("use pid", usePid);
        SmartDashboard.putNumber("arm angle", getAngle());
        if(usePid) {
            if(isOverRotated()) return;
            double volts = controller.calculate(getAngle()); /* + calculateFeedForward(controller.getSetpoint().velocity); */ // double check this logic
            SmartDashboard.putNumber("Volt reading", volts);
            SmartDashboard.putNumber("Goal angle", controller.getGoal().position);
            volts = MathUtil.clamp(volts, -3, 3);
            if(isUnderRotated()) {
                volts = Math.max(volts, 0);
            }
            pivotMotor.setVoltage(volts);
        }
    }
}