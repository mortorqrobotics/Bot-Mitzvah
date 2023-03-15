package org.team1515.botmitzvah.Commands.Autonomous.AutoCommands;

import org.team1515.botmitzvah.Commands.Autonomous.DriveCommands.DriveDistProfiled;
import org.team1515.botmitzvah.Subsystems.*;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AutoCommandLeave extends SequentialCommandGroup{
    public AutoCommandLeave(Drivetrain drivetrain) { // add params
        addCommands(
                new DriveDistProfiled(drivetrain, Units.inchesToMeters(170), 1),
                new DriveDistProfiled(drivetrain, Units.feetToMeters(4.5), -1));
    }
}
