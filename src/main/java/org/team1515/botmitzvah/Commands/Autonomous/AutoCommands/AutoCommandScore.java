package org.team1515.botmitzvah.Commands.Autonomous.AutoCommands;

import org.team1515.botmitzvah.RobotMap;
import org.team1515.botmitzvah.Commands.Autonomous.AutoArmSet;
import org.team1515.botmitzvah.Commands.Autonomous.AutoPivotSet;
import org.team1515.botmitzvah.Commands.Autonomous.DriveCommands.DriveDistProfiled;
import org.team1515.botmitzvah.Subsystems.*;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.Commands;
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
            new InstantCommand(()->claw.intake()),
            // new AutoArmSet(arm, RobotMap.ARM_RETRACTED_POS),
            new AutoPivotSet(pivot, RobotMap.ARM_PIVOT_MID_ANGLE),
            new WaitCommand(0.25),
            new InstantCommand(() -> claw.outtake()),
            new WaitCommand(0.5),
            new InstantCommand(() -> claw.end())
            // new DriveDistProfiled(drivetrain, Units.inchesToMeters(140), 1)
            // Commands.parallel(new AutoPivotSet(pivot, RobotMap.ARM_PIVOT_STOWED_ANGLE),
            // new DriveDistProfiled(drivetrain, Units.inchesToMeters(170), -1))
        );
    }
}
