package org.team1515.botmitzvah.Commands.Autonomous;

import org.team1515.botmitzvah.Commands.Autonomous.AutoArmAndPivot.AutoArmSet;
import org.team1515.botmitzvah.Commands.Autonomous.DriveCommands.DriveDist;
import org.team1515.botmitzvah.Subsystems.*;
import org.team1515.botmitzvah.Subsystems.Arm.Extension;

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
    public AutoCommandScore(Drivetrain drivetrain, Claw claw, ArmPivot pivot, Arm arm) { // add params
        addCommands(
                new InstantCommand(() -> claw.extend()),
                new AutoArmSet(arm, Extension.Retracted),
                new InstantCommand(() -> pivot.setAngle(0)),
                new AutoArmSet(arm, Extension.Extended),
                new InstantCommand(() -> claw.retract()),
                new DriveDist(drivetrain, Units.inchesToMeters(140), 1)
        );
    }
}
