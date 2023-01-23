package org.team1515.botmitzvah.Commands;

import org.team1515.botmitzvah.Robot;
import org.team1515.botmitzvah.RobotMap;
import org.team1515.botmitzvah.Subsystems.Drivetrain;

import com.team364.swervelib.util.SwerveConstants;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Translation2d;

public class AlignLight extends CommandBase {
    private Drivetrain drivetrainSubsystem;
    private PIDController controller;
    private double maxSpeed;

    /**
     * Align robot with the target using the limelight
     * 
     * @param drivetrainSubsystem
     * @param limelight
     */
    public AlignLight(Drivetrain drivetrainSubsystem) {
        this.drivetrainSubsystem = drivetrainSubsystem;
        this.maxSpeed = RobotMap.ALIGN_POS_LIMIT * SwerveConstants.Swerve.maxAngularVelocity;

        angleController = new PIDController(5, 6.5, 0);
        // TODO retune PID
        controller.setTolerance(0.025);
        controller.enableContinuousInput(-Math.PI, Math.PI);
        controller.setSetpoint(0.0);

        addRequirements(drivetrainSubsystem);
    }

    @Override
    public void execute() {
        double error = Robot.limelight.getTX(); // TX should be in meters
        if (error == 0) // Stop auto align if limelight has no target in view
            this.end(true);
        double rotation = MathUtil.clamp(angleController.calculate(error, 0.0), -maxSpeed, maxSpeed);
        drivetrainSubsystem.drive(new Translation2d(0.0, 0.0), rotation, false, true);
    }

    @Override
    public boolean isFinished() {
        return controller.atSetpoint();
    }
}
