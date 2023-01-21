package org.team1515.botmitzvah.Commands.Autonomous;

import org.team1515.botmitzvah.RobotContainer;
import org.team1515.botmitzvah.RobotMap;
import org.team1515.botmitzvah.Subsystems.Drivetrain;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoBalance extends CommandBase {
    // PID Controller
    // input -> gyro angle
    // setpoint -> 0
    // position tolerance within rules (if within rules, drivtrain should break)
    // (+/- 1.5 deg, need to convert)
    // output -> drivetrain speeds (or poistion if you wanna be field relative,
    // dependent on drift & odometry)\
    private PIDController controller;
    private Drivetrain drivetrain;
    private double maxSpeed;

    public AutoBalance(Drivetrain drivetrain, double maxSpeed) {
        this.drivetrain = drivetrain;

        controller = new PIDController(RobotMap.BALANCE_KP, RobotMap.BALANCE_KI, RobotMap.BALANCE_KD); // retun PID
        controller.setTolerance(Units.degreesToRadians(1.5));
        controller.enableContinuousInput(-Math.PI, Math.PI);
        controller.setSetpoint(0.0);

        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        double error = Math.toRadians(RobotContainer.gyro.getPitch());
        double speed = MathUtil.clamp(controller.calculate(error, 0.0), -maxSpeed, maxSpeed);
        drivetrain.drive(new Translation2d(speed, 0.0), 0.0, false, true);
    }

    @Override
    public boolean isFinished() {
        return controller.atSetpoint();
    }

}
