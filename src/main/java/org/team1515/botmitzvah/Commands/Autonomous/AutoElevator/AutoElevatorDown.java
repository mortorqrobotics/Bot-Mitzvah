package org.team1515.botmitzvah.Commands.Autonomous.AutoElevator;

import org.team1515.botmitzvah.Subsystems.Elevator;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoElevatorDown extends CommandBase {

    private final Elevator elevator;

    public AutoElevatorDown(Elevator elevator) {
        this.elevator = elevator;
        addRequirements(elevator);
    }

    @Override
    public void execute() {
        elevator.retract();
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
