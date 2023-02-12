package org.team1515.botmitzvah.Commands.Autonomous;

import org.team1515.botmitzvah.RobotMap;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.filter.LinearFilter;https://github.com/mortorqrobotics/Bot-Mitzvah
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoBalance extends CommandBase {
    private PIDController controller;
    private Swerve drivetrain;
    private double maxSpeed;
    private LinearFilter filter = LinearFilter.movingAverage(5);

    private double p = 3;
    private double i = 1.6;
    private double d = 0.55;
    private int count = 0;

    public AutoBalance(Swerve drivetrain) {
        this.drivetrain = drivetrain;
        this.maxSpeed = 0.2 * RobotMap.MAX_SPEED;

        controller = new PIDController(p,i,d); // retun PID
        controller.setTolerance(Units.degreesToRadians(1));
        controller.enableContinuousInput(-Math.PI, Math.PI);
        controller.setSetpoint(0);

        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        double error = Math.toRadians(filter.calculate(RobotContainer.gyro.getRoll()));
        double speed = MathUtil.clamp(controller.calculate(-error, 0.0), -maxSpeed, maxSpeed);
        drivetrain.drive(new Translation2d(speed, 0.0), 0.0, false, true);

        System.out.println(speed);
        
        if (controller.atSetpoint()){
            count++;
        }
        else{
            count = 0;
        }
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.drive(new Translation2d(0.0,0.0), 1, false, true);
    }

    @Override
    public boolean isFinished() {
        return controller.atSetpoint() && count > 40;
    }
}
