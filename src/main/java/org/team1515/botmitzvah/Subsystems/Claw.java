package org.team1515.botmitzvah.Subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Claw extends SubsystemBase {
    private DoubleSolenoid piston;
    private boolean extended = false;

    public Claw(int forwardID, int reverseID) {
        piston = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, forwardID, reverseID); // add parameter if we change
                                                                                         // PCM ID
        piston.set(Value.kReverse); // check if this runs in robotInit
    }

    public void extend() {
        if (!extended) {
            piston.set(Value.kForward);
        }
        extended = true;
    }

    public void retract() {
        if (!extended) {
            piston.set(Value.kReverse);
        }
        extended = false;
    }

    public void toggle() {
        piston.toggle();
    }

    public boolean getExtended() {
        return extended;
    }

}
