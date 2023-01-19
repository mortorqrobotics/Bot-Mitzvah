package org.team1515.botmitzvah.Commands.Autonomous;

import org.team1515.botmitzvah.RobotContainer;
import org.team1515.botmitzvah.Robot;

import org.team1515.botmitzvah.Commands.Align;
import org.team1515.botmitzvah.Commands.DriveDist;

import org.team1515.botmitzvah.Subsystems.Drivetrain;
//add other subsystems

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class AutoCommandBalance extends SequentialCommandGroup {

    /**
     * Runs auto one command after another (finished when the isFinished method
     * returns true)
     * 
     * @param # add params
     */
    public AutoCommandBalance(Drivetrain drivetrain) { // add params
        addCommands(
        // add commands in order and wait when needed
        );
    }
}