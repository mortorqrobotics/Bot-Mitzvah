package org.team1515.botmitzvah.Commands.Autonomous.DriveCommands;

import org.team1515.botmitzvah.RobotContainer;
import org.team1515.botmitzvah.Subsystems.Drivetrain;

import com.team364.swervelib.util.SwerveConstants;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RotateToAngle extends CommandBase {
    private Drivetrain drivetrain;
    private Rotation2d targetAngle;
    private PIDController angleController;

    private double maxSpeed = 0.25 * SwerveConstants.Swerve.maxAngularVelocity;
    private double setpoint = 0;
    private double deadband = 0.2;

    public RotateToAngle(Drivetrain drivetrain, Rotation2d targetAngle) {
        this.drivetrain = drivetrain;
        this.targetAngle = targetAngle;

        angleController = new PIDController(1, 2, 0);
        angleController.setTolerance(deadband);
        angleController.setSetpoint(setpoint);

        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        double angleOffset = RobotContainer.gyro.getGyroscopeRotation().minus(targetAngle).getRadians();
        double rotation = MathUtil.clamp(angleController.calculate(angleOffset, setpoint), -maxSpeed, maxSpeed);

        drivetrain.drive(new Translation2d(0.0, 0.0), rotation, true, true);
    }

    @Override
    public boolean isFinished() {
        return angleController.atSetpoint();
    }
}