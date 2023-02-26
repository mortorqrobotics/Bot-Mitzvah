package org.team1515.botmitzvah.Commands.Autonomous;

import org.team1515.botmitzvah.RobotContainer;
import org.team1515.botmitzvah.Commands.ClawClose;
import org.team1515.botmitzvah.Commands.Autonomous.AutoArmAndPivot.AutoArmSet;
import org.team1515.botmitzvah.Subsystems.Arm;
import org.team1515.botmitzvah.Subsystems.ArmPivot;
import org.team1515.botmitzvah.Subsystems.Arm.Extension;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class StartupCommand extends SequentialCommandGroup {
    public StartupCommand(Arm arm, ArmPivot pivot) {
        addCommands(
            new InstantCommand(() -> RobotContainer.drivetrain.zeroGyro()),
            new ClawClose(RobotContainer.claw),
            new AutoArmSet(RobotContainer.arm, Extension.Retracted),
            new InstantCommand(() -> RobotContainer.armPivot.usePid = true)
        );
    }
}
