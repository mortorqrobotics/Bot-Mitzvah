package org.team1515.botmitzvah.Subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import org.team1515.botmitzvah.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;



public class Arm extends SubsystemBase{
    private TalonFX m_arm;
    public static final double speed = 0.5;

    public Arm(){
        m_arm = new TalonFX(RobotMap.ARM_ID);
    }
    
    public void extend() {
        m_arm.set(ControlMode.PercentOutput, speed);
    }

    public void retract() {
        m_arm.set(ControlMode.PercentOutput, -speed);
    }

    public void end() {
        m_arm.set(ControlMode.PercentOutput, 0);
    }
}
