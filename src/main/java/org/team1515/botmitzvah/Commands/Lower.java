package org.team1515.botmitzvah.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import org.team1515.botmitzvah.Subsystems.Elevator;

public class Lower extends CommandBase {
    private final Elevator elevator;

    public Lower(Elevator elevator) {
        this.elevator = elevator;
        addRequirements(elevator);
    }

    @Override
    public void execute() {
        elevator.retract();
    }

    @Override
    public void end(boolean interrupted) {
        elevator.end();
    }

    @Override
    public boolean isFinished() {
        return elevator.getLower();
    }
}