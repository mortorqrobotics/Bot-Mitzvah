package org.team1515.botmitzvah.Utils;

import org.team1515.botmitzvah.RobotMap;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class AprilTag {

    private NetworkTable table;
    private NetworkTableEntry tx;
    private NetworkTableEntry ty;
    private NetworkTableEntry ta;

    /**
     * Offset in degrees for the camera (use if the camera isn't perfectly centered
     * on the robot)
     */
    private double txOffset = 0;

    public AprilTag() {
        table = NetworkTableInstance.getDefault().getTable("apriltag");
        tx = table.getEntry("tx");
        ty = table.getEntry("ty");
        ta = table.getEntry("ta");
    }

    /**
     * @return double Horizontal Offset From Crosshair To Target (-27 degrees to 27
     *         degrees)
     */
    public double getTX() {
        return tx.getDouble(0.0) + txOffset;
    }

    /**
     * @return double Target Area (0% of image to 100% of image)
     */
    public double getTA() {
        return ta.getDouble(0.0);
    }

    /**
     * @return double Vertical Offset From Crosshair To Target (-20.5 degrees to
     *         20.5 degrees)
     */
    public double getTY() {
        return ty.getDouble(0.0);
    }

    /**
     * @return double Get distance in inches
     */
    public double getDistance() {
        double deltaHeight = RobotMap.HEIGHT_OF_TAG - RobotMap.HEIGHT_OF_WEBCAM;
        return (deltaHeight / Math.tan(Math.toRadians(getTY() + RobotMap.ANGLE_OF_WEBCAM)))
                + RobotMap.WEBCAM_OFFSET;
    }

}
