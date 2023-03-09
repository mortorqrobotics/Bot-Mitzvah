package org.team1515.botmitzvah.Commands.Autonomous.AutoCommands;
import org.team1515.botmitzvah.RobotMap;
import org.team1515.botmitzvah.Commands.ClawOut;
import org.team1515.botmitzvah.Commands.Autonomous.AutoArmSet;
import org.team1515.botmitzvah.Commands.Autonomous.DriveDist;
import org.team1515.botmitzvah.Subsystems.*;
import org.team1515.botmitzvah.Subsystems.Arm.Extension;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
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
                new AutoArmSet(arm, Extension.Retracted),
                new InstantCommand(() -> pivot.setAngle(RobotMap.ARM_PIVOT_TOP_DEG)),
                new AutoArmSet(arm, Extension.Extended),
                new ClawOut(claw),
                new ParallelCommandGroup(
                    Commands.sequence(new AutoArmSet(arm, Extension.Retracted), new InstantCommand(() -> pivot.setAngle(RobotMap.ARM_PIVOT_STOWED_DEG))), 
                    new DriveDist(drivetrain, Units.inchesToMeters(140), 1))
        );
    }
}
