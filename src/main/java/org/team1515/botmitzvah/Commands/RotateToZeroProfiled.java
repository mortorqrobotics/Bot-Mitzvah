package org.team1515.botmitzvah.Commands;

import org.team1515.botmitzvah.RobotContainer;
import org.team1515.botmitzvah.Subsystems.Drivetrain;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.ProfiledPIDCommand;

public class RotateToZeroProfiled extends ProfiledPIDCommand {
    private static double p = 2;
    private static double i = 0;
    private static double d = 0;
    private static double ff = 1.0;
    private static double maxRotVel = 0.26;
    private static double maxRotAcc = 0.016;

    public RotateToZeroProfiled(Drivetrain drivetrainSubsystem) {
        super(
                new ProfiledPIDController(
                        p,
                        i,
                        d,
                        new TrapezoidProfile.Constraints(
                                maxRotVel,
                                maxRotAcc)),
                // Close loop on heading
                () -> Math.toRadians(RobotContainer.gyro.getYaw()),
                // Set reference to target
                0,
                // Pipe output to turn robot
                (output, setpoint) -> drivetrainSubsystem.drive(new Translation2d(0.0, 0.0), output + Math.copySign(ff, output), true, true),
                // Require the drive
                drivetrainSubsystem);

        getController().enableContinuousInput(-Math.PI, Math.PI);
        getController()
                .setTolerance(3.5, 0.8);
    }

    @Override
    public boolean isFinished() {
        // End when the controller is at the reference.
        return getController().atGoal();
    }
}
