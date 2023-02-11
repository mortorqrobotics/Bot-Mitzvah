package org.team1515.botmitzvah.Commands.Autonomous.AutoElevator;

import edu.wpi.first.wpilibj2.command.CommandBase;

import org.team1515.botmitzvah.Subsystems.Elevator;

/**
 * autonomously moves robot elevator to middle position using a limit switch
 * changes state of isOut variable when touching limit switch
 * if arm is past the middle switch, retract, otherwise expand
 */

public class AutoElevatorRetract extends CommandBase {
    private final Elevator elevator;

    public AutoElevatorRetract(Elevator elevator) {
        this.elevator = elevator;
        addRequirements(elevator);
    }

    @Override
    public void execute() {
        elevator.goToRetracted();
    }

    @Override
    public void end(boolean interrupted) {
        elevator.end();
    }

    @Override
    public boolean isFinished() {
        return elevator.isAtSetPoint();
    }
}