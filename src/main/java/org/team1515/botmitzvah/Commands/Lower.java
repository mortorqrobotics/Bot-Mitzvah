package org.team1515.botmitzvah.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import org.team1515.botmitzvah.Subsystems.Elevator;

/** An example command that uses an example subsystem. */
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
    public boolean isFinished() {
        return true;
    }
}