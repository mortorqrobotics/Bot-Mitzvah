package org.team1515.botmitzvah.Commands.Autonomous.AutoCommands;

import org.team1515.botmitzvah.RobotMap;
import org.team1515.botmitzvah.Commands.ClawOut;
import org.team1515.botmitzvah.Commands.Autonomous.AutoArmSet;
import org.team1515.botmitzvah.Commands.Autonomous.AutoPivotSet;
import org.team1515.botmitzvah.Commands.Autonomous.DriveCommands.DriveDist;
import org.team1515.botmitzvah.Commands.Autonomous.DriveCommands.DriveDistProfiled;
import org.team1515.botmitzvah.Commands.ManualArmAndPivot.ArmRetract;
import org.team1515.botmitzvah.Subsystems.*;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class AutoCommandScore extends SequentialCommandGroup {

    /**
     * Runs auto one command after another (finished when the isFinished method
     * returns true)
     * 
     * @param # add params
     */
    public AutoCommandScore(Drivetrain drivetrain, ArmPivot pivot, Claw claw, Arm arm) { // add params
        addCommands(
            new AutoArmSet(arm, RobotMap.ARM_RETRACTED_POS),
            new AutoPivotSet(pivot, RobotMap.ARM_PIVOT_MID_ANGLE),
            new ClawOut(claw),
            new DriveDistProfiled(drivetrain, 0, -1)
        );
    }
}
