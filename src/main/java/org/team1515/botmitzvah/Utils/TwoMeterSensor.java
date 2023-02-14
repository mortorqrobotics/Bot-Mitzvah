package org.team1515.botmitzvah.Utils;

import org.team1515.botmitzvah.RobotMap;

import com.revrobotics.Rev2mDistanceSensor;
import com.revrobotics.Rev2mDistanceSensor.Port;

public class TwoMeterSensor {
    private Rev2mDistanceSensor distFront; 
    private Rev2mDistanceSensor distBack;

    public TwoMeterSensor(){
        distFront = new Rev2mDistanceSensor(Port.kOnboard);
        distBack = new Rev2mDistanceSensor(Port.kMXP);
    }

    /**
     * @return boolean true if at an edge and false if on the ground
     */
    public boolean getEdgeBound(){
        return (distFront.getRange() >= RobotMap.MAX_EDGE_BOUND) || (distBack.getRange() >= RobotMap.MAX_EDGE_BOUND);
    }
}
