package org.team1515.botmitzvah.Commands.Autonomous;

import org.team1515.botmitzvah.RobotMap;
import org.team1515.botmitzvah.Commands.*;
import org.team1515.botmitzvah.Commands.Autonomous.AutoArmAndPivot.*;
import org.team1515.botmitzvah.Commands.Autonomous.DriveCommands.DriveDist;
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
    public AutoCommandScore(Drivetrain drivetrain, Arm arm, ArmPivot armPivot, Claw claw) { // add params
        addCommands(
                new InstantCommand(() -> drivetrain.zeroGyro()),
                new ClawClose(claw),
                Commands.parallel(new AutoArmSet(arm, RobotMap.ARM_TOP_POS), new AutoPivotSet(armPivot, RobotMap.ARM_PIVOT_TOP_DEG)),
                new ClawOpen(claw),
                Commands.parallel(new AutoArmSet(arm, RobotMap.ARM_BOTTOM_POS),
                        new AutoPivotSet(armPivot, RobotMap.ARM_PIVOT_BOTTOM_DEG),
                        new DriveDist(drivetrain, Units.feetToMeters(3 + RobotMap.CHARGING_STATION_DISTANCE + 6), -1)));
        // might seperate if tipping
    }
}