package org.team1515.botmitzvah;

import java.util.HashMap;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.DriverStation;

public class RobotMap {

    // Conversion factors
    public final static double FALCON_SENSOR_UNITS = 4096.0;

    // PDH
    public static final int PDH_ID = -1; // replace

    // Subsystems

    // Claw
    public final static int CLAW_FORWARD_ID = -1;// replace
    public final static int CLAW_REVERSE_ID = -1;// replace

    // Arm
    public final static int ARM_ID = -1;// replace
    public static final int H_OUTER_SWITCH_ID = -1;// replace
    public static final int H_MIDDLE_SWITCH_ID = -1;// replace
    public static final int H_INNER_SWITCH_ID = -1;// replace

    // Elevator
    public final static int ELEVATOR_ID = -1;// replace
    public static final int V_UPPER_SWITCH_ID = -1;// replace
    public static final int V_LOWER_SWITCH_ID = -1;// replace
    public static final int V_MIDDLE_SWITCH_ID = -1;// replace

    // Limelight
    public final static double HEIGHT_OF_TAPE = -1;// replace
    public final static double HEIGHT_OF_LIMELIGHT = -1;// replace
    public final static double ANGLE_OF_LIMELIGHT = -1;// replace
    public final static double LIMELIGHT_OFFSET = -1;// replace

    // Limelight
    public final static double HEIGHT_OF_TAG = -1;// replace
    public final static double HEIGHT_OF_WEBCAM = -1;// replace
    public final static double ANGLE_OF_WEBCAM = -1;// replace
    public final static double WEBCAM_OFFSET = -1;// replace
    public final static double DISTANCE_OFFSET = -1;// replace

    // balance PID
    public static final double BALANCE_KP = -1;// replace
    public static final double BALANCE_KI = -1;// replace
    public static final double BALANCE_KD = -1;// replace

    // align pos PID
    public static final double ALIGN_POS_KP = -1;// replace
    public static final double ALIGN_POS_KI = -1;// replace
    public static final double ALIGN_POS_KD = -1;// replace

    // speed limits
    public static final double SWERVE_LIMIT = 0.5;// replace
    public static final double BALANCE_LIMIT = 0.5;// replace
    public static final double ALIGN_POS_LIMIT = 0.5;// replace
    public static final double ALIGN_ANGLE_LIMIT = 0.5;

    // pids
    public static final double SWERVE_KP = 0;
    public static final double SWERVE_KI = 0;
    public static final double SWERVE_KD = 0;

    public static final double ALIGN_ANGLE_KP = 0;
    public static final double ALIGN_ANGLE_KI = 0;
    public static final double ALIGN_ANGLE_KD = 0;

    // photonVision
    public static final String CAMERA_NAME = "name";
    public static final Transform3d ROBOT_TO_CAM = new Transform3d(new Translation3d(0.5, 0.0, 0.5),
            new Rotation3d(0, 0, 0));
    // TODO replace transform values

    // starting values for pose estimator
    public static final Pose2d[] STARTING_RED = {
            new Pose2d(Units.feetToMeters(4.5), Units.feetToMeters(3), Rotation2d.fromRadians(0.0)),
            new Pose2d(Units.feetToMeters(4.5), Units.feetToMeters(9), Rotation2d.fromRadians(0.0)),
            new Pose2d(Units.feetToMeters(4.5), Units.feetToMeters(15), Rotation2d.fromRadians(0.0))
    };
    public static final Pose2d[] STARTING_BLUE = {
            new Pose2d(Units.feetToMeters(4.5), Units.feetToMeters(3), Rotation2d.fromRadians(0.0)),
            new Pose2d(Units.feetToMeters(4.5), Units.feetToMeters(9), Rotation2d.fromRadians(0.0)),
            new Pose2d(Units.feetToMeters(4.5), Units.feetToMeters(15), Rotation2d.fromRadians(0.0))
    };
}
