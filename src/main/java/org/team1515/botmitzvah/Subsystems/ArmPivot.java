package org.team1515.botmitzvah.Subsystems;

import org.team1515.botmitzvah.RobotContainer;
import org.team1515.botmitzvah.RobotMap;

import com.ctre.phoenix.sensors.AbsoluteSensorRange;
import com.ctre.phoenix.sensors.CANCoder;
import com.ctre.phoenix.sensors.CANCoderConfiguration;
import com.ctre.phoenix.sensors.SensorInitializationStrategy;
import com.ctre.phoenix.sensors.SensorTimeBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmPivot extends SubsystemBase {
    private CANSparkMax pivotMotor;
    private CANCoder encoder;

    public static double upSpeed = RobotMap.ARM_PIVOT_UP_SPEED;
    public static double downSpeed = RobotMap.ARM_PIVOT_DOWN_SPEED;

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
        if(getCancoderAngle() > 45) {
            end();
            return;
        };
        pivotMotor.set(upSpeed * RobotContainer.secondController.getRightY());
    }

    public void lower() {
        pivotMotor.set(-downSpeed * RobotContainer.secondController.getRightY());
    }

    public void setSpeed(double speed) {
        if(getCancoderAngle() > 45 && speed > 0) {
            end();
            return;
        };
        if(getCancoderAngle() < -50 && speed < 0) {
            end();
            return;
        }
        pivotMotor.set(speed);
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

    @Override
    public void periodic() {
        SmartDashboard.putNumber("angle", encoder.getAbsolutePosition());
    }
}