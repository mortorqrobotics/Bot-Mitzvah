package org.team1515.botmitzvah.Commands.Autonomous.AutoArm;

import edu.wpi.first.wpilibj2.command.CommandBase;

import org.team1515.botmitzvah.Subsystems.Arm;

/** An example command that uses an example subsystem. */
public class AutoArmIn extends CommandBase {
    private final Arm arm;

    public AutoArmIn(Arm arm) {
        this.arm = arm;
        addRequirements(arm);
    }

    @Override
    public void execute() {
        // if (!arm.getInner()) {
        arm.retract();
        // }
    }

    @Override
    public void end(boolean interrupted) {
        if(arm.getInner()){
            arm.setIsOut(false);
        }
        arm.end();
    }

    @Override
    public boolean isFinished() {
        return arm.getInner();
    }
}