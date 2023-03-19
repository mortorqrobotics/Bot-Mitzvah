package org.team1515.botmitzvah.Commands.Autonomous.AutoCommands;

import org.team1515.botmitzvah.RobotContainer;
import org.team1515.botmitzvah.Commands.Autonomous.DriveCommands.AutoBalanceSus;
import org.team1515.botmitzvah.Commands.Autonomous.DriveCommands.DriveDist;
import org.team1515.botmitzvah.Commands.Autonomous.DriveCommands.DriveDistProfiled;
import org.team1515.botmitzvah.Subsystems.*;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
// import edu.wpi.first.wpilibj2.command.WaitCommand;

public class OldAutoCommandBalance extends SequentialCommandGroup {

    /**
     * Runs auto one command after another (finished when the isFinished method
     * returns true)
     * 
     * @param # add params
     */
    public OldAutoCommandBalance(Drivetrain drivetrain) { // add params
        addCommands(
                new InstantCommand(() -> RobotContainer.gyro.zeroRoll()),
                new DriveDistProfiled(drivetrain, Units.inchesToMeters(170), 1, 1, 7),
                new DriveDistProfiled(drivetrain, Units.inchesToMeters(57), -1),
                new AutoBalanceSus(drivetrain, -1)
                // new DriveDistProfiled(drivetrain, Units.inchesToMeters(17), 1),
                // new InstantCommand(()-> drivetrain.drive(new Translation2d(0,0),1, false, true ))
                // 
        );
    }
}
