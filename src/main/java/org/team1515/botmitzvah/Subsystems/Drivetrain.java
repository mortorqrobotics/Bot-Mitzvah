package org.team1515.botmitzvah.Subsystems;

import com.team364.swervelib.util.SwerveModule;
import com.team364.swervelib.util.SwerveConstants;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModulePosition;

import java.util.Optional;

import org.photonvision.EstimatedRobotPose;
import org.team1515.botmitzvah.RobotContainer;

import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
    public SwerveModule[] mSwerveMods;

    private SwerveDrivePoseEstimator poseEstimator;
    private final Field2d field2d = new Field2d();

    private Rotation2d realZero;

    public Drivetrain(Pose2d initialPos) {
        realZero = initialPos.getRotation();

        zeroGyro();

        mSwerveMods = new SwerveModule[] {
                new SwerveModule(0, SwerveConstants.Swerve.Mod0.constants),
                new SwerveModule(1, SwerveConstants.Swerve.Mod1.constants),
                new SwerveModule(2, SwerveConstants.Swerve.Mod2.constants),
                new SwerveModule(3, SwerveConstants.Swerve.Mod3.constants)
        };

        poseEstimator = new SwerveDrivePoseEstimator(SwerveConstants.Swerve.swerveKinematics, Rotation2d.fromRadians(0),
                getModulePositions(), initialPos);

        /*
         * By pausing init for a second before setting module offsets, we avoid a bug
         * with inverting motors.
         * See https://github.com/Team364/BaseFalconSwerve/issues/8 for more info.
         */
        Timer.delay(1.0);
        resetModulesToAbsolute();

        SmartDashboard.putData("Field", field2d);
    }

    /**
     * 
     * @param translation   speed translation in m/s
     * @param rotation      in r/s
     * @param fieldRelative if robot is robot or field reletive
     * @param isOpenLoop    does not use velocity pid?
     */

    public void drive(Translation2d translation, double rotation, boolean fieldRelative, boolean isOpenLoop) {
        SwerveModuleState[] swerveModuleStates = SwerveConstants.Swerve.swerveKinematics.toSwerveModuleStates(
                fieldRelative ? ChassisSpeeds.fromFieldRelativeSpeeds(
                        translation.getX(),
                        translation.getY(),
                        rotation,
                        getYaw())
                        : new ChassisSpeeds(
                                translation.getX(),
                                translation.getY(),
                                rotation));
        SwerveDriveKinematics.desaturateWheelSpeeds(swerveModuleStates, SwerveConstants.Swerve.maxSpeed);

        for (SwerveModule mod : mSwerveMods) {
            mod.setDesiredState(swerveModuleStates[mod.moduleNumber], isOpenLoop);
        }
    }

    /* Used by SwerveControllerCommand in Auto */
    public void setModuleStates(SwerveModuleState[] desiredStates) {
        SwerveDriveKinematics.desaturateWheelSpeeds(desiredStates, SwerveConstants.Swerve.maxSpeed);

        for (SwerveModule mod : mSwerveMods) {
            mod.setDesiredState(desiredStates[mod.moduleNumber], false);
        }
    }

    public SwerveModuleState[] getModuleStates() {
        SwerveModuleState[] states = new SwerveModuleState[4];
        for (SwerveModule mod : mSwerveMods) {
            states[mod.moduleNumber] = mod.getState();
        }
        return states;
    }

    public SwerveModulePosition[] getModulePositions() {
        SwerveModulePosition[] positions = new SwerveModulePosition[4];
        for (SwerveModule mod : mSwerveMods) {
            positions[mod.moduleNumber] = mod.getPosition();
        }
        return positions;
    }

    /**
     * resets robot yaw to zero
     */
    public void zeroGyro() {
        realZero = realZero.minus(RobotContainer.gyro.getGyroscopeRotation());
        RobotContainer.gyro.zeroYaw();
    }

    public Pose2d getPose() {
        return poseEstimator.getEstimatedPosition();
    }

    /**
     * @return Rotation2d yaw of the robot in radians
     */
    public Rotation2d getYaw() {
        return (SwerveConstants.Swerve.invertGyro) ? Rotation2d.fromDegrees(360 - RobotContainer.gyro.getYaw())
                : Rotation2d.fromDegrees(RobotContainer.gyro.getYaw());
    }

    public void resetModulesToAbsolute() {
        for (SwerveModule mod : mSwerveMods) {
            mod.resetToAbsolute();
        }
    }

    public void updateOdometry() {
        poseEstimator.update(
                RobotContainer.gyro.getGyroscopeRotation(), getModulePositions());

        // Also apply vision measurements. We use 0.3 seconds in the past as an example
        // -- on
        // a real robot, this must be calculated based either on latency or timestamps.
        Optional<EstimatedRobotPose> result = RobotContainer.pvw
                .getEstimatedGlobalPose(poseEstimator.getEstimatedPosition());

        if (result.isPresent()) {
            EstimatedRobotPose camPose = result.get();
            poseEstimator.addVisionMeasurement(
                    camPose.estimatedPose.toPose2d(), camPose.timestampSeconds);
        }

        field2d.setRobotPose(getPose());
    }

    @Override
    public void periodic() {
        poseEstimator.update(getYaw(), getModulePositions());

        for (SwerveModule mod : mSwerveMods) {
            SmartDashboard.putNumber("Mod " + mod.moduleNumber + " Cancoder", mod.getCanCoder().getDegrees());
            SmartDashboard.putNumber("Mod " + mod.moduleNumber + " Integrated", mod.getPosition().angle.getDegrees());
            SmartDashboard.putNumber("Mod " + mod.moduleNumber + " Velocity", mod.getState().speedMetersPerSecond);
        }
    }

    /**
     * @return Rotation2d offset from starting zero to currect zero
     */
    public Rotation2d getRealZero() {
        return realZero;
    }
}