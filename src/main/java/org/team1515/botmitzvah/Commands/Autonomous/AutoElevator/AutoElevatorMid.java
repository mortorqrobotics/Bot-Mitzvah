package org.team1515.botmitzvah.Commands.Autonomous.AutoElevator;

import org.team1515.botmitzvah.Subsystems.Elevator;

import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * autonomously moves robot elevator to middle position using a limit switch
 * changes state of isOut variable when touching limit switch
 * if elevator is past the middle switch, retract, otherwise expand
 */

public class AutoElevatorMid extends CommandBase {

    private Elevator elevator;

    public AutoElevatorMid(Elevator elevator) {
        this.elevator = elevator;
        addRequirements(elevator);
    }

    @Override
    public void execute() {
        elevator.goToMiddle();
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