package org.team1515.botmitzvah.Commands.Autonomous;

import org.team1515.botmitzvah.Commands.*;
import org.team1515.botmitzvah.Commands.Autonomous.AutoArm.*;
import org.team1515.botmitzvah.Commands.Autonomous.AutoElevator.*;

import org.team1515.botmitzvah.Subsystems.*;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
// import edu.wpi.first.wpilibj2.command.WaitCommand;

public class AutoCommandScore extends SequentialCommandGroup {

    /**
     * Runs auto one command after another (finished when the isFinished method
     * returns true)
     * 
     * @param # add params
     */
    public AutoCommandScore(Drivetrain drivetrain, Arm arm, Elevator elevator, Claw claw) { // add params
        addCommands(
                new InstantCommand(() -> drivetrain.zeroGyro()),
                new ClawClose(claw),
                Commands.parallel(new AutoArmOut(arm), new AutoElevatorUp(elevator)),
                new ClawOpen(claw),
                Commands.parallel(new AutoArmIn(arm),
                        new AutoElevatorUp(elevator),
                        new DriveDist(drivetrain, Units.feetToMeters(12.5), -1, 0.5)));
        // might seperate if tipping
    }
}