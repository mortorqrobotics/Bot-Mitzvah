package org.team1515.botmitzvah.Commands.Autonomous;

import org.team1515.botmitzvah.Commands.Autonomous.DriveCommands.AutoBalance;
import org.team1515.botmitzvah.Commands.Autonomous.DriveCommands.DriveDist;
import org.team1515.botmitzvah.Subsystems.*;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
// import edu.wpi.first.wpilibj2.command.WaitCommand;

public class AutoCommandBalance extends SequentialCommandGroup {

    /**
     * Runs auto one command after another (finished when the isFinished method
     * returns true)
     * 
     * @param # add params
     */
    public AutoCommandBalance(Drivetrain drivetrain) { // add params
        addCommands(
                new InstantCommand(() -> drivetrain.zeroGyro()),
                new DriveDist(drivetrain, Units.feetToMeters(12.5), 1),
                new DriveDist(drivetrain, Units.feetToMeters(2.5), -1),
                new AutoBalance(drivetrain));
    }
}