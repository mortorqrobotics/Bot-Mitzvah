package org.team1515.botmitzvah.Utils;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.geometry.Pose2d;
import org.team1515.botmitzvah.RobotMap;

import java.io.IOException;
import java.util.Optional;
import org.photonvision.EstimatedRobotPose;
import org.photonvision.PhotonCamera;
import org.photonvision.PhotonPoseEstimator;
import org.photonvision.PhotonPoseEstimator.PoseStrategy;

public class PhotonVisionWrapper {
    public PhotonCamera photonCamera;
    public PhotonPoseEstimator photonPoseEstimator;

    public PhotonVisionWrapper() {
        // Set up a test arena of two apriltags at the center of each driver station set
        AprilTagFieldLayout aprilTagFieldLayout;
        try {
            aprilTagFieldLayout = AprilTagFieldLayout.loadFromResource(AprilTagFields.k2023ChargedUp.m_resourceFile);
        } catch (IOException e) {
            aprilTagFieldLayout = new AprilTagFieldLayout(null, 0, 0);
        }

        // Forward Camera
        photonCamera = new PhotonCamera(
                RobotMap.CAMERA_NAME); // Change the name of your camera here to whatever it is in the
        // PhotonVision UI.

        // Create pose estimator
        photonPoseEstimator = new PhotonPoseEstimator(
                aprilTagFieldLayout, PoseStrategy.CLOSEST_TO_REFERENCE_POSE, photonCamera, RobotMap.ROBOT_TO_CAM);
    }

    /**
     * @param estimatedRobotPose The current best guess at robot pose
     * @return A pair of the fused camera observations to a single Pose2d on the
     *         field, and the time
     *         of the observation. Assumes a planar field and the robot is always
     *         firmly on the ground
     */
    public Optional<EstimatedRobotPose> getEstimatedGlobalPose(Pose2d prevEstimatedRobotPose) {
        photonPoseEstimator.setReferencePose(prevEstimatedRobotPose);
        return photonPoseEstimator.update();
    }
}