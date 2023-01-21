package org.team1515.botmitzvah.Commands;

import org.team1515.botmitzvah.Robot;
import org.team1515.botmitzvah.Subsystems.Drivetrain;
import org.team1515.botmitzvah.Utils.AprilTag;

import com.team364.swervelib.util.SwerveConstants;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.kinematics.ChassisSpeeds;

public class AlignTag extends CommandBase {
    private Drivetrain drivetrainSubsystem;
    //l
    private PIDController angleController;
    private double maxSpeed = 0.75 * SwerveConstants.Swerve.maxAngularVelocity;

    /**
     * Align robot with the target using the limelight
     * 
     * @param drivetrainSubsystem
     * @param limelight
     */
    public AlignTag(Drivetrain drivetrainSubsystem) {
        this.drivetrainSubsystem = drivetrainSubsystem;

        angleController = new PIDController(5, 6.5, 0);
        // TODO retune PID
        angleController.setTolerance(0.025);
        angleController.enableContinuousInput(-Math.PI, Math.PI);
        angleController.setSetpoint(0.0);

        addRequirements(drivetrainSubsystem);
    }

    @Override
    public void execute() {
        double error = Math.toRadians(Robot.apriltag.getTX());
        if (error == 0) // Stop auto align if limelight has no target in view
            this.end(true);
        double speed = MathUtil.clamp(angleController.calculate(error, 0.0), -maxSpeed, maxSpeed);

        ChassisSpeeds speeds = new ChassisSpeeds(0.0, 0.0, speed);
        // drivetrainSubsystem.drive(speeds); figure out params
    }

    @Override
    public boolean isFinished() {
        return angleController.atSetpoint();
    }
}
