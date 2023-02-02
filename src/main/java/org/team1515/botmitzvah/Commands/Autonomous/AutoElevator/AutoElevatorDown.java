package org.team1515.botmitzvah.Commands.Autonomous.AutoElevator;

import org.team1515.botmitzvah.Subsystems.Elevator;

import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * autonomously moves robot elevator to lower position using a limit switch
 */

public class AutoElevatorDown extends CommandBase {

    private final Elevator elevator;

    public AutoElevatorDown(Elevator elevator) {
        this.elevator = elevator;
        addRequirements(elevator);
    }

    @Override
    public void execute() {
        elevator.goToLower();
    }

    @Override
    public void end(boolean interrupted) {
        if (elevator.getLower()) {
            elevator.setIsOut(false);
        }
        elevator.end();
    }

    @Override
    public boolean isFinished() {
        return elevator.getLower();
    }
}
