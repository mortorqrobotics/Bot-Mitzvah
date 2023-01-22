package org.team1515.botmitzvah.Commands;

import org.team1515.botmitzvah.Robot;
import org.team1515.botmitzvah.Subsystems.Drivetrain;

import com.team364.swervelib.util.SwerveConstants;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Translation2d;

public class AlignTag extends CommandBase {
    private Drivetrain drivetrainSubsystem;
    // l
    private PIDController controller;
    private double maxSpeed = 0.75 * SwerveConstants.Swerve.maxAngularVelocity;

    /**
     * Align robot with the target using the limelight
     * 
     * @param drivetrainSubsystem
     * @param limelight
     */
    public AlignTag(Drivetrain drivetrainSubsystem) {
        this.drivetrainSubsystem = drivetrainSubsystem;

        controller = new PIDController(5, 6.5, 0);
        // TODO retune PID
        controller.setTolerance(0.025);
        controller.enableContinuousInput(-Math.PI, Math.PI);
        controller.setSetpoint(0.0);

        addRequirements(drivetrainSubsystem);
    }

    @Override
    public void execute() {
        double error = Robot.apriltag.getTX(); //TX should be in meters
        if (error == 0) // Stop auto align if limelight has no target in view
            this.end(true);
        double speed = MathUtil.clamp(controller.calculate(error, 0.0), -maxSpeed, maxSpeed);
        drivetrainSubsystem.drive(new Translation2d(0.0, speed), 0.0, false, true);
    }

    @Override
    public boolean isFinished() {
        return controller.atSetpoint();
    }
}
