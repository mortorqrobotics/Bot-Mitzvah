package org.team1515.botmitzvah.Utils;

import org.team1515.botmitzvah.RobotMap;

import com.revrobotics.Rev2mDistanceSensor;

public class TwoMeterSensor {
    private Rev2mDistanceSensor distOnboard; 
    private Rev2mDistanceSensor distMXP;

    public TwoMeterSensor(Rev2mDistanceSensor Onboard, Rev2mDistanceSensor XMP){
        distOnboard = Onboard;
        distMXP = XMP;
    }

    public boolean getEdgeBound(){
        return (distOnboard.getRange() >= RobotMap.MAX_EDGE_BOUND) || (distMXP.getRange() >= RobotMap.MAX_EDGE_BOUND);
    }
}
