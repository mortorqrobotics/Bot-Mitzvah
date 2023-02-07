package org.team1515.botmitzvah.Commands.Autonomous.DriveCommands;

import org.team1515.botmitzvah.RobotContainer;
import org.team1515.botmitzvah.RobotMap;
import org.team1515.botmitzvah.Subsystems.Drivetrain;

import com.team364.swervelib.util.SwerveConstants;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.filter.LinearFilter;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoBalance extends CommandBase {
    private PIDController controller;
    private Drivetrain drivetrain;
    private double maxSpeed;
    private LinearFilter filter = LinearFilter.movingAverage(5);

    public AutoBalance(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;
        this.maxSpeed = RobotMap.BALANCE_LIMIT * SwerveConstants.Swerve.maxSpeed;

        controller = new PIDController(RobotMap.BALANCE_KP, RobotMap.BALANCE_KI, RobotMap.BALANCE_KD); // retun PID
        controller.setTolerance(Units.degreesToRadians(1.5));
        controller.enableContinuousInput(-Math.PI, Math.PI);
        controller.setSetpoint(0.0);

        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        double error = Math.toRadians(filter.calculate(RobotContainer.gyro.getPitch()));
        double speed = MathUtil.clamp(controller.calculate(error, 0.0), -maxSpeed, maxSpeed);
        drivetrain.drive(new Translation2d(speed, 0.0), 0.0, false, true);
    }

    @Override
    public boolean isFinished() {
        return controller.atSetpoint() || RobotContainer.ir.getEdgeBound();
    }
}