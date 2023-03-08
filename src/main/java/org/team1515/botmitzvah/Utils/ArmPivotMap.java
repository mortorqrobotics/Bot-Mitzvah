package org.team1515.botmitzvah.Utils;

import org.team1515.botmitzvah.RobotMap;
import org.team1515.botmitzvah.Subsystems.Arm.Extension;

import edu.wpi.first.util.InterpolatingTreeMap;

public class ArmPivotMap {
    // angle of pivot, volts
    private InterpolatingTreeMap<Double, Double> outExtensionMap;
    private InterpolatingTreeMap<Double, Double> inExtensionMap; 

    public ArmPivotMap() {
        outExtensionMap = new InterpolatingTreeMap<>();
        inExtensionMap = new InterpolatingTreeMap<>();

        /*
         * Station (1 extension)
         * Bottom (2 extension)
         * Middle (1 extension)
         * Top (1 extension)
         */

         /* angle, voltage */
         /* out extension */
        outExtensionMap.put(RobotMap.ARM_PIVOT_TOP_DEG, 0d);
        outExtensionMap.put(RobotMap.ARM_PIVOT_MID_DEG, 0d);
        outExtensionMap.put(RobotMap.ARM_PIVOT_BOTTOM_DEG, 0d);

        /* in extension */
        inExtensionMap.put(RobotMap.ARM_PIVOT_MID_DEG, 0d);
        inExtensionMap.put(RobotMap.ARM_PIVOT_BOTTOM_DEG, 0d);
        inExtensionMap.put(RobotMap.ARM_PIVOT_STOWED_DEG, 0d);
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
            return outExtensionMap.get(angle) * Math.signum(velocity);
        }
        else if(extension == Extension.Retracted){
            return inExtensionMap.get(angle) * Math.signum(velocity);
        }
        else {
            return 0;
        }
    }
}
