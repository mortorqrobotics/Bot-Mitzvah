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
    private PIDController posController;
    private double maxSpeed;
    private PIDController angleController;
    private double maxRotate;

    /**
     * Align robot with the target using the limelight
     * 
     * @param drivetrainSubsystem
     * @param limelight
     */
    public AlignLight(Drivetrain drivetrainSubsystem) {
        this.drivetrainSubsystem = drivetrainSubsystem;
        this.maxSpeed = RobotMap.ALIGN_POS_LIMIT * SwerveConstants.Swerve.maxSpeed;
        this.maxRotate = RobotMap.ALIGN_POS_LIMIT * SwerveConstants.Swerve.maxAngularVelocity;

        posController = new PIDController(RobotMap.ALIGN_POS_KP, RobotMap.ALIGN_POS_KI, RobotMap.ALIGN_POS_KD);
        // TODO retune PID
        posController.setTolerance(0.025);
        posController.setSetpoint(0.0);

        angleController = new PIDController(RobotMap.ALIGN_ANGLE_KP, RobotMap.ALIGN_ANGLE_KI, RobotMap.ALIGN_ANGLE_KD);
        // TODO retune PID
        angleController.setTolerance(0.025);
        angleController.enableContinuousInput(-Math.PI, Math.PI);
        angleController.setSetpoint(0.0);

        addRequirements(drivetrainSubsystem);
    }

    @Override
    public void execute() {
        double error = Robot.limelight.getTX(); // TX should be in meters
        if (error == 0) // Stop auto align if limelight has no target in view
            this.end(true);
        double speed = MathUtil.clamp(posController.calculate(error, 0.0), -maxSpeed, maxSpeed);
        double rotation = MathUtil.clamp(angleController.calculate(error, 0.0), -maxRotate, maxRotate);
        drivetrainSubsystem.drive(new Translation2d(0.0, speed), rotation, true, true);
    }

    @Override
    public boolean isFinished() {
        return posController.atSetpoint() && angleController.atSetpoint();
    }
}
