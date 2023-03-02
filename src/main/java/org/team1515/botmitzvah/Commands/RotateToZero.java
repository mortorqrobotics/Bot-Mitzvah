package org.team1515.botmitzvah.Commands;

import org.team1515.botmitzvah.RobotContainer;
import org.team1515.botmitzvah.Subsystems.Drivetrain;

import com.team364.swervelib.util.SwerveConstants;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RotateToZero extends CommandBase {
    private Drivetrain drivetrainSubsystem;
    private PIDController angleController;
    private double maxRotate;

    private double p = 2;
    private double i = 0;
    private double d = 0;
    private double ff = 1.0;
    private double angle;

    /**
     * Align robot with the target using the gyro
     * 
     * @param drivetrainSubsystem
     */
    public RotateToZero(Drivetrain drivetrainSubsystem) {
        this.drivetrainSubsystem = drivetrainSubsystem;
        this.maxRotate = SwerveConstants.Swerve.maxAngularVelocity;
        angleController = new PIDController(p, i, d);
        angleController.setTolerance(Units.degreesToRadians(3.5));
        angleController.enableContinuousInput(-Math.PI, Math.PI);
        angleController.setSetpoint(0.0);

        addRequirements(drivetrainSubsystem);
    }

    @Override
    public void execute() {
        double error = MathUtil.angleModulus(RobotContainer.gyro.getGyroscopeRotation().getRadians()) - (drivetrainSubsystem.getRealZero().getRadians() + angle);
        double rotation = (MathUtil.clamp(angleController.calculate(error, 0.0)+(ff*Math.signum(-error)), -maxRotate, maxRotate));

        drivetrainSubsystem.drive(new Translation2d(0.0, 0.0), rotation, true, true);
    }

    @Override
    public boolean isFinished() {
        return angleController.atSetpoint();
    }
}