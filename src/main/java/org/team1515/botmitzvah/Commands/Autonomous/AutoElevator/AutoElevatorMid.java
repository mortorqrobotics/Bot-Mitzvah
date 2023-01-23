package org.team1515.botmitzvah.Commands.Autonomous.AutoElevator;

import org.team1515.botmitzvah.Subsystems.Elevator;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoElevatorMid extends CommandBase {

    private Elevator elevator;

    public AutoElevatorMid(Elevator elevator) {
        this.elevator = elevator;
        addRequirements(elevator);
    }

    @Override
    public void execute() {
        if (elevator.getIsOut()) {
            elevator.retract();
        } else {
            elevator.extend();
        }
    }

    @Override
    public void end(boolean interrupted) {
        if (elevator.getMiddle()) {
            if (elevator.getIsOut()) {
                elevator.setIsOut(false);
            } else {
                elevator.setIsOut(true);
            }
        }
        elevator.end();
    }

    @Override
    public boolean isFinished() {
        return elevator.getMiddle();
    }

}