package org.team1515.botmitzvah.Commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import org.team1515.botmitzvah.Subsystems.Drivetrain;

import com.team364.swervelib.util.SwerveConstants;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DefaultDriveCommand extends CommandBase {
    private Drivetrain drivetrain;
    private DoubleSupplier translationSup;
    private DoubleSupplier strafeSup;
    private DoubleSupplier rotationSup;
    private BooleanSupplier robotCentricSup;

    public DefaultDriveCommand(Drivetrain drivetrain, DoubleSupplier translationSup, DoubleSupplier strafeSup,
            DoubleSupplier rotationSup, BooleanSupplier robotCentricSup) {
        this.drivetrain = drivetrain;
        addRequirements(drivetrain);

        this.translationSup = translationSup;
        this.strafeSup = strafeSup;
        this.rotationSup = rotationSup;
        this.robotCentricSup = robotCentricSup;
    }

    @Override
    public void execute() {
        /* Get Values, Deadband */
        double translationVal = MathUtil.applyDeadband(translationSup.getAsDouble(), SwerveConstants.stickDeadband);
        double strafeVal = MathUtil.applyDeadband(strafeSup.getAsDouble(), SwerveConstants.stickDeadband);
        double rotationVal = MathUtil.applyDeadband(rotationSup.getAsDouble(), SwerveConstants.stickDeadband);

        /* Drive */
        drivetrain.drive(
                new Translation2d(translationVal, strafeVal).times(SwerveConstants.Swerve.maxSpeed),
                rotationVal * SwerveConstants.Swerve.maxAngularVelocity,
                !robotCentricSup.getAsBoolean(),
                true);
    }
}