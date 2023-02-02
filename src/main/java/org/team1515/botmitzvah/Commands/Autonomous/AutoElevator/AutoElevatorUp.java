package org.team1515.botmitzvah.Commands.Autonomous.AutoElevator;

import org.team1515.botmitzvah.Subsystems.Elevator;

import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * autonomously moves robot elevator to upper position using a limit switch
 */

public class AutoElevatorUp extends CommandBase {

    private final Elevator elevator;

    public AutoElevatorUp(Elevator elevator) {
        this.elevator = elevator;
        addRequirements(elevator);
    }

    @Override
    public void execute() {
        elevator.extend();
    }

    @Override
    public void end(boolean interrupted) {
        if (elevator.getUpper()) {
            elevator.setIsOut(true);
        }
        elevator.end();
    }

    @Override
    public boolean isFinished() {
        return elevator.getUpper();
    }

}
