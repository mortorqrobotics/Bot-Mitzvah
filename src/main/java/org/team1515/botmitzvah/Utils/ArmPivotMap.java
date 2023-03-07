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
        outExtensionMap.put(0d, 0d);
        outExtensionMap.put(0d, 0d);
        outExtensionMap.put(0d, 0d);
        outExtensionMap.put(0d, 0d);

        /* in extension */
        inExtensionMap.put(0d, 0d);
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
