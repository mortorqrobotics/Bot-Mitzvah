package org.team1515.botmitzvah.Subsystems;

import org.team1515.botmitzvah.RobotMap;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Elevator extends SubsystemBase {
    private TalonFX elevator;
    public static final double speed = 0.5;

    public Elevator() {
        elevator = new TalonFX(RobotMap.ELEVATOR_ID);
    }

    public void extend() {
        elevator.set(ControlMode.PercentOutput, speed);
    }

    public void retract() {
        elevator.set(ControlMode.PercentOutput, -speed);
    }

    public void end() {
        elevator.set(ControlMode.PercentOutput, 0);
    }
}
