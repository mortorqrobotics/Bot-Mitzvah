package org.team1515.botmitzvah.Commands.Autonomous.DriveCommands;

import org.team1515.botmitzvah.RobotContainer;
import org.team1515.botmitzvah.Subsystems.Drivetrain;
import org.team1515.botmitzvah.Utils.Utilities;

import com.team364.swervelib.util.SwerveConstants;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoBalanceSus extends CommandBase {
    private Drivetrain drivetrain;
    private double maxSpeed;
    private double direction;

    public AutoBalanceSus(Drivetrain drivetrain, double direction) {
        this.drivetrain = drivetrain;
        this.maxSpeed = 0.09 * SwerveConstants.Swerve.maxSpeed;
        this.direction = direction;

        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        drivetrain.drive(new Translation2d(maxSpeed * direction, 0.0), 0.0, false, true);
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.drive(new Translation2d(0.0,0.0), 1, false, true);
    }

    @Override
    public boolean isFinished() {
        double error = RobotContainer.gyro.getRoll();
        return Utilities.epsilonEquals(error, 0, 3);
    }
}