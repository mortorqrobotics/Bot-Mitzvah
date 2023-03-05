package org.team1515.botmitzvah.Commands.Autonomous;

import org.team1515.botmitzvah.RobotMap;
import org.team1515.botmitzvah.Commands.ClawClose;
import org.team1515.botmitzvah.Commands.Autonomous.DriveCommands.AutoBalance;
import org.team1515.botmitzvah.Commands.Autonomous.DriveCommands.DriveDist;
import org.team1515.botmitzvah.Subsystems.*;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
// import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class AutoCommandScore extends SequentialCommandGroup {

    /**
     * Runs auto one command after another (finished when the isFinished method
     * returns true)
     * 
     * @param # add params
     */
    public AutoCommandScore(Drivetrain drivetrain, Claw claw) { // add params
        addCommands(
                new ClawClose(claw),
                new DriveDist(drivetrain, Units.inchesToMeters(174), 1),
                new WaitCommand(0.5),
                new DriveDist(drivetrain, Units.inchesToMeters(87), -1, 0.5),
                new WaitCommand(0.0459),
                new InstantCommand(() -> drivetrain.drive(new Translation2d(0.0,0.0), 1, false, true))
            );

    }
}
