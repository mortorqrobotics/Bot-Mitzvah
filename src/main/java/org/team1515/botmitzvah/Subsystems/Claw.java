package org.team1515.botmitzvah.Subsystems;

import org.team1515.botmitzvah.RobotMap;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Claw extends SubsystemBase {
    private CANSparkMax lClaw;
    private CANSparkMax rClaw;

    public Claw() {
        lClaw = new CANSparkMax(RobotMap.L_CLAW_ID, MotorType.kBrushless);
        rClaw = new CANSparkMax(RobotMap.R_CLAW_ID, MotorType.kBrushless);
    }

    public void intake() {
        lClaw.set(RobotMap.CLAW_SPEED);
        rClaw.set(RobotMap.CLAW_SPEED);
    }

    public void outtake() {
        lClaw.set(-RobotMap.CLAW_SPEED);
        rClaw.set(-RobotMap.CLAW_SPEED);
    }

    public void end() {
        lClaw.set(0);
        rClaw.set(0);
    }
}