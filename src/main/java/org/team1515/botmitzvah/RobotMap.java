package org.team1515.botmitzvah;

public class RobotMap {

        // Conversion factors
        public final static double FALCON_SENSOR_UNITS = 4096.0;

        // PDH
        public static final int PDH_ID = -1; // replace

        // Subsystems

        // Claw
        public static final int L_CLAW_ID = 0;
        public static final int R_CLAW_ID = 0;
        public static final double CLAW_SPEED = 0;

        // speed limits for swerve
        public static final double SWERVE_LIMIT = 0.5;// replace
        public static final double BALANCE_LIMIT = 0.5;// replace
        public static final double ALIGN_POS_LIMIT = 0.5;// replace
        public static final double ALIGN_ANGLE_LIMIT = 0.5;

        // Arm Constants
        public final static int ARM_ID = 15;// replace
        public final static double ARM_SPEED = 0.5;// replace

        public static final double ARM_RETRACTED_POS = -1; // replace
        public static final double ARM_EXTENDED_POS = -1; // replace
        public static final double ARM_UPPER_LIMIT = -1;
        public static final double ARM_LOWER_LIMIT = -1;
        public static final double ARM_TOLERANCE = -1; // replace

        // ArmPivot Constants
        public static final int ARM_PIVOT_ID = 14; // replace
        public static final int ARM_PIVOT_CANCODER_ID = 13;

        public static final double ARM_PIVOT_UPPER_LIMIT = 50;
        public static final double ARM_PIVOT_EXTENDED_LOWER_LIMIT = -30;
        public static final double ARM_PIVOT_RETRACTED_LOWER_LIMIT = -70;
        public static final double ARM_PIVOT_TOP_DEG = -1; // replace
        public static final double ARM_PIVOT_MID_DEG = -1; // replace
        public static final double ARM_PIVOT_BOTTOM_DEG = -1; // replace
        public static final double ARM_PIVOT_STOWED_DEG = -1; // replace

        /** degrees */
        public static final double ARM_PIVOT_OFFSET = 241; // MEASURE (ZERO SHOULD BE REFERENCE FROM HORIZONTAL)
        public static final double ARM_PIVOT_GEAR_RATIO = 90; // replace

        public static final double ARM_PIVOT_SPEED = 0.25;
        public static final double ARM_PIVOT_MAX_VELOCITY = -1; // replace (in radians per second)
        public static final double ARM_PIVOT_MAX_ACCELERATION = -1; // replace (in radians per second per second)

        // swerve pids
        public static final double BALANCE_KP = 3; // replace
        public static final double BALANCE_KI = 1.6; // replace
        public static final double BALANCE_KD = 0.55; // replace

        public static final double ALIGN_POS_KP = -1; // replace
        public static final double ALIGN_POS_KI = -1; // replace
        public static final double ALIGN_POS_KD = -1; // replace

        public static final double ALIGN_ANGLE_KP = 2; // replace
        public static final double ALIGN_ANGLE_KI = 0; // replace
        public static final double ALIGN_ANGLE_KD = 1; // replace

        //public static final double CHARGING_STATION_DISTANCE = (48 + 2 * 9.125) / 12; // units in feet
}