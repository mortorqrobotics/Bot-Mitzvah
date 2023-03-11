package org.team1515.botmitzvah.Commands.Autonomous;

import org.team1515.botmitzvah.RobotMap;
import org.team1515.botmitzvah.Commands.ClawOut;
import org.team1515.botmitzvah.Commands.Autonomous.DriveCommands.AutoBalance;
import org.team1515.botmitzvah.Commands.Autonomous.DriveCommands.DriveDist;
import org.team1515.botmitzvah.Subsystems.*;

import edu.wpi.first.math.util.Units;
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
    public AutoCommandScore(Drivetrain drivetrain, Claw claw) { // add params
        addCommands(
                new DriveDist(drivetrain, Units.inchesToMeters(170), 1),
                new DriveDist(drivetrain, Units.feetToMeters(4.5), -1));
    }
}
