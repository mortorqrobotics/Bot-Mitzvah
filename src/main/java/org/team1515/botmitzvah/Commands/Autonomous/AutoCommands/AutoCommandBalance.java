package org.team1515.botmitzvah.Commands.Autonomous.AutoCommands;

import org.team1515.botmitzvah.Commands.Autonomous.AutoBalance;
import org.team1515.botmitzvah.Commands.Autonomous.DriveDist;
import org.team1515.botmitzvah.Subsystems.*;

import edu.wpi.first.math.util.Units;
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
                new DriveDist(drivetrain, Units.inchesToMeters(170), 1),
                new DriveDist(drivetrain, Units.feetToMeters(4.5), -1),
                new AutoBalance(drivetrain)
        );
    }
}
