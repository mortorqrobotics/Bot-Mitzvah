package org.team1515.botmitzvah.Utils;

import org.team1515.botmitzvah.RobotMap;
import org.team1515.botmitzvah.Subsystems.Arm.Extension;

import edu.wpi.first.util.InterpolatingTreeMap;

public class ArmPivotMap {
    // angle of pivot, volts
    private InterpolatingTreeMap<Double, Double> outHoldingExtensionMap;
    private InterpolatingTreeMap<Double, Double> inHoldingExtensionMap;
    private InterpolatingTreeMap<Double, Double> outEmptyExtensionMap;
    private InterpolatingTreeMap<Double, Double> inEmptyExtensionMap;  

    public ArmPivotMap() {
        outHoldingExtensionMap = new InterpolatingTreeMap<>();
        inHoldingExtensionMap = new InterpolatingTreeMap<>();
        outEmptyExtensionMap = new InterpolatingTreeMap<>();
        inEmptyExtensionMap = new InterpolatingTreeMap<>();

        /*
         * Station (1 extension)
         * Bottom (2 extension)
         * Middle (1 extension)
         * Top (1 extension)
         */

         /* angle, voltage */
        outHoldingExtensionMap.put(RobotMap.ARM_PIVOT_TOP_DEG, 0d);
        outHoldingExtensionMap.put(RobotMap.ARM_PIVOT_MID_DEG, 0d);
        outHoldingExtensionMap.put(RobotMap.ARM_PIVOT_BOTTOM_DEG, 0d);

        inHoldingExtensionMap.put(RobotMap.ARM_PIVOT_MID_DEG, 0d);
        inHoldingExtensionMap.put(RobotMap.ARM_PIVOT_BOTTOM_DEG, 0d);
        inHoldingExtensionMap.put(RobotMap.ARM_PIVOT_STOWED_DEG, 0d);

        outEmptyExtensionMap.put(RobotMap.ARM_PIVOT_TOP_DEG, 0d);
        outEmptyExtensionMap.put(RobotMap.ARM_PIVOT_MID_DEG, 0d);
        outEmptyExtensionMap.put(RobotMap.ARM_PIVOT_BOTTOM_DEG, 0d);

        inEmptyExtensionMap.put(RobotMap.ARM_PIVOT_MID_DEG, 0d);
        inEmptyExtensionMap.put(RobotMap.ARM_PIVOT_BOTTOM_DEG, 0d);
        inEmptyExtensionMap.put(RobotMap.ARM_PIVOT_STOWED_DEG, 0d);
    }

    /**
     * @param angle angle of the arm in degrees (0 is parallel to the ground)
     * @param extension extension state
     * @return interpolated value at an extension
     */
    public double calculate(double angle, double velocity, Extension extension) {
        if(velocity < -0.05) {
            return 0;
        }
        if(extension == Extension.Extended) {
            return outEmptyExtensionMap.get(angle) * Math.signum(velocity);
        }
        else if(extension == Extension.Retracted){
            return inEmptyExtensionMap.get(angle) * Math.signum(velocity);
        }
        else {
            return 0;
        }
    }
}
