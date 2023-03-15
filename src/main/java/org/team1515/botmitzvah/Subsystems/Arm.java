package org.team1515.botmitzvah.Subsystems;

import org.team1515.botmitzvah.RobotMap;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
    private CANSparkMax arm;
    private RelativeEncoder encoder;

    public static double speed;
    public static boolean useLimits = true;

    public Arm() {
        arm = new CANSparkMax(RobotMap.ARM_ID, MotorType.kBrushless);
        arm.restoreFactoryDefaults();
        arm.setInverted(false);

        arm.setIdleMode(IdleMode.kBrake);
        arm.burnFlash();

        encoder = arm.getEncoder();
        resetArmPosition(0);
        SmartDashboard.putData("set arm to 0", new InstantCommand(() -> resetArmPosition(0)));
        SmartDashboard.putData("UseLimits", new InstantCommand(()->useLimits = !useLimits));
        // encoder.setInverted(false);

        speed = RobotMap.ARM_SPEED;
    }

    public void extend() {
        // if (useLimits && getArmPosition()<RobotMap.ARM_MAX_POS){
            arm.set(speed);
        // }
    }

    public void retract() {
        // if (useLimits && getArmPosition()>RobotMap.ARM_MIN_POS){
        arm.set(-speed);//}
    }

    public void setSpeed(double velocity) {
        arm.set(velocity);
    }

    public double getArmPosition() {
        return encoder.getPosition();
    }

    public void resetArmPosition(double position) {
        encoder.setPosition(position);
    }

    public void end() {
        arm.set(0);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("arm pos", getArmPosition());
    }
}