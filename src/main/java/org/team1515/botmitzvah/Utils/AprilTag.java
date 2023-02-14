package org.team1515.botmitzvah.Utils;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;
import org.team1515.botmitzvah.RobotMap;

import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.util.Units;

public class AprilTag {
    private PhotonCamera camera;
    PhotonPipelineResult result;
    private double yaw;
    private double h_offset;
    private double distance;

    public AprilTag() {
        camera = new PhotonCamera(RobotMap.CAMERA_NAME);
    }

    public void update() {
        result = camera.getLatestResult();
        if (result.hasTargets()) {
            yaw = result.getBestTarget().getYaw();
            Transform3d camToTarget = result.getBestTarget().getBestCameraToTarget();
            distance = camToTarget.getX();
            h_offset = camToTarget.getY();
        }
    }

    /**
     * @return double yaw in radians
     */
    public double getYaw() {
        return Units.degreesToRadians(yaw);
    }

    /**
     * @return double distance in meters
     */
    public double getDistance() {
        return distance;
    }

    /**
     * returns horizontal offset in meters
     * 
     * @return
     */
    public double getHOffset() {
        return h_offset;
    }
}