package org.team1515.botmitzvah.Subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;

import org.team1515.botmitzvah.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Claw extends SubsystemBase {
    private DoubleSolenoid pistonL;
    private DoubleSolenoid pistonR;
    private boolean extended = false;

    public Claw() {

        pistonL = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, RobotMap.LEFT_CLAW_FORWARD_ID, RobotMap.LEFT_CLAW_REVERSE_ID);
        pistonR = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, RobotMap.RIGHT_CLAW_FORWARD_ID, RobotMap.RIGHT_CLAW_REVERSE_ID);
        // PCM ID
        //pistonL.set(Value.kReverse); // check if this runs in robotInit
        //pistonR.set(Value.kReverse);
    }

    public void extend() {
        if (!extended) {
            pistonL.set(Value.kForward);
            pistonR.set(Value.kForward);
        }
        extended = true;
    }

    public void retract() {
        if (extended) {
            pistonL.set(Value.kReverse);
            pistonR.set(Value.kReverse);
        }
        extended = false;
    }

    public void toggle() {
        pistonL.toggle();
        pistonR.toggle();
    }

    public boolean getExtended() {
        return extended;
    }

}
