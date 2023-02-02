package org.team1515.botmitzvah.Commands;

import org.team1515.botmitzvah.RobotContainer;
import org.team1515.botmitzvah.RobotMap;
import org.team1515.botmitzvah.Subsystems.Drivetrain;

import com.team364.swervelib.util.SwerveConstants;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Translation2d;

public class ZeroRobotGyro extends CommandBase {
    private Drivetrain drivetrainSubsystem;
    // l
    private PIDController angleController;
    private double maxRotate;

    private double ff = 3.23; // retune

    /**
     * Align robot with the target using the limelight
     * 
     * @param drivetrainSubsystem
     * @param limelight
     */
    public ZeroRobotGyro(Drivetrain drivetrainSubsystem) {
        this.drivetrainSubsystem = drivetrainSubsystem;
        this.maxRotate = RobotMap.ALIGN_ANGLE_LIMIT * SwerveConstants.Swerve.maxAngularVelocity;

        angleController = new PIDController(RobotMap.ALIGN_ANGLE_KP, RobotMap.ALIGN_ANGLE_KI, RobotMap.ALIGN_ANGLE_KD);
        // TODO retune PID
        angleController.setTolerance(0.025);
        angleController.enableContinuousInput(-Math.PI, Math.PI);
        angleController.setSetpoint(0.0);

        addRequirements(drivetrainSubsystem);
    }

    @Override
    public void execute() {
        double error = MathUtil.angleModulus(RobotContainer.gyro.getGyroscopeRotation().getRadians())
                - drivetrainSubsystem.getRealZero().getRadians();
        double rotation = (MathUtil.clamp(angleController.calculate(error, 0.0) + (ff * Math.signum(-error)),
                -maxRotate, maxRotate));
        drivetrainSubsystem.drive(new Translation2d(0.0, 0.0), rotation, true, true);
    }

    @Override
    public boolean isFinished() {
        return angleController.atSetpoint();
    }
}