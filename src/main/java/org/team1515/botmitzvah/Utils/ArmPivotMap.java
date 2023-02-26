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
        outExtensionMap.put(RobotMap.ARM_PIVOT_BOTTOM_DEG, RobotMap.ARM_PIVOT_BOTTOM_OUT_VOLTS);
        outExtensionMap.put(RobotMap.ARM_PIVOT_MIDDLE_DEG, RobotMap.ARM_PIVOT_MIDDLE_OUT_VOLTS);
        outExtensionMap.put(RobotMap.ARM_PIVOT_TOP_DEG, RobotMap.ARM_PIVOT_TOP_OUT_VOLTS);
        outExtensionMap.put(RobotMap.ARM_PIVOT_STATION_DEG, RobotMap.ARM_PIVOT_STATION_OUT_VOLTS);

        /* in extension */
        inExtensionMap.put(RobotMap.ARM_PIVOT_BOTTOM_DEG, RobotMap.ARM_PIVOT_BOTTOM_IN_VOLTS);
    }

    /**
     * @param extension extension state
     * @return interpolated value at an extension
     */
    public double get(double angle, Extension extension) {
        if(extension == Extension.Extended) {
            return outExtensionMap.get(angle);
        }
        else if(extension == Extension.Retracted){
            return inExtensionMap.get(angle);
        }
        else {
            return 0;
        }
    }
}
