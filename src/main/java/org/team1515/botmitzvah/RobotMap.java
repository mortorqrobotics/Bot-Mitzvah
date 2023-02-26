package org.team1515.botmitzvah;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.util.Units;

public class RobotMap {

    // Conversion factors
    public final static double FALCON_SENSOR_UNITS = 4096.0;

    // PDH
    public static final int PDH_ID = -1; // replace

    // Subsystems

    // Claw
    public final static int CLAW_FORWARD_ID = -1;// replace
    public final static int CLAW_REVERSE_ID = -1;// replace

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

    // speed limits for swerve
    public static final double SWERVE_LIMIT = 0.5;// replace
    public static final double BALANCE_LIMIT = 0.5;// replace
    public static final double ALIGN_POS_LIMIT = 0.5;// replace
    public static final double ALIGN_ANGLE_LIMIT = 0.5;

    //Arm Constants
    public final static int ARM_ID = -1;// replace
    public final static double ARM_SPEED = 0.5;// replace

    public static final double ARM_RETRACTED_POS = -1; // replace
    public static final double ARM_EXTENDED_POS = -1; // replace
    public static final double ARM_MAX_POS = -1; // replace
    public static final double ARM_MIN_POS = -1; // replace
    public static final double ARM_TOLERANCE = -1; // replace
     
    // ArmPivot Constants
    public static final int ARM_PIVOT_ID = -1; // replace
    public static final int ARM_PIVOT_CANCODER_ID = -1;

    public static final double ARM_PIVOT_KP = -1; // replace
    public static final double ARM_PIVOT_KI = -1; // replace
    public static final double ARM_PIVOT_KD = -1; // replace


    /** degrees */
    public static final double ARM_PIVOT_OFFSET = 0; // MEASURE (ZERO SHOULD BE REFERENCE FROM HORIZONTAL)
    public static final double ARM_PIVOT_GEAR_RATIO = -1; // replace

    // all angles in degrees
    public static final double ARM_PIVOT_TOP_DEG = -1; // replace
    public static final double ARM_PIVOT_MIDDLE_DEG = -1; // replace
    public static final double ARM_PIVOT_BOTTOM_DEG = -1; // replace
    public static final double ARM_PIVOT_STATION_DEG = -1; // replace
    public static final double ARM_PIVOT_MIN_DEG = -1; // replace
    public static final double ARM_PIVOT_MAX_DEG = -1;

    public static final double ARM_PIVOT_SPEED = 0.7;
    public static final double ARM_PIVOT_MAX_VELOCITY = -1; // replace (in radians per second)
    public static final double ARM_PIVOT_MAX_ACCELERATION = -1; // replace (in radians per second per second)
    
    public static final double ARM_PIVOT_STATION_OUT_VOLTS = -1;
    public static final double ARM_PIVOT_BOTTOM_OUT_VOLTS = -1;
    public static final double ARM_PIVOT_MIDDLE_OUT_VOLTS = -1;
    public static final double ARM_PIVOT_TOP_OUT_VOLTS = -1;
    public static final double ARM_PIVOT_BOTTOM_IN_VOLTS = -1;

    // swerve pids
    public static final double BALANCE_KP = 3; // replace
    public static final double BALANCE_KI = 1.6; // replace
    public static final double BALANCE_KD = 0.55; // replace

    public static final double ALIGN_POS_KP = -1; // replace
    public static final double ALIGN_POS_KI = -1; // replace
    public static final double ALIGN_POS_KD = -1; // replace

    public static final double ALIGN_ANGLE_KP = -1; // replace
    public static final double ALIGN_ANGLE_KI = -1; // replace
    public static final double ALIGN_ANGLE_KD = -1; // replace

    // Lidar Sensor
    public static final double MAX_EDGE_BOUND = 0;

    public static final double CHARGING_STATION_DISTANCE = (48 + 2 * 9.125) / 12; // units in feet
}