package org.team1515.botmitzvah.Commands.Autonomous.CalcCommands;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;

import org.team1515.botmitzvah.Subsystems.Drivetrain;

import java.lang.Math;

public class driveCircle extends CommandBase {
    private final Drivetrain drivetrain;
    private double r; //radius of the circle
    private double ti; //initial time parameter
    private double tf; //final time parameter
    private boolean isClockwise; //is a clockwise or conterclockwise circle
    private double realTime; //actial system time
    private double i; //i vector component of r'
    private double j; //i vector component of r'
    public driveCircle(double r, double ti, double tf, boolean isClockwise, Drivetrain drivetrain) {
        this.drivetrain = drivetrain;
        this.r = r;
        this.ti = ti;
        this.tf = tf;
        this.isClockwise = isClockwise;
        realTime = System.currentTimeMillis();
    }

    @Override
    public void execute() {
        if(!isClockwise){
            i = -r*Math.sin(ti+(System.currentTimeMillis()-realTime));
            j = r*Math.cos(ti+(System.currentTimeMillis()-realTime));
        }
        else{
            i = r*Math.cos(ti+(System.currentTimeMillis()-realTime));
            j = -r*Math.sin(ti+(System.currentTimeMillis()-realTime));
        }
        drivetrain.drive(new Translation2d(i,j), 0, true,true);
    }

    @Override
    public boolean isFinished() {
        return System.currentTimeMillis()-realTime >= tf-ti;
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.drive(new Translation2d(0.0, 0.0), 0.0, false, false);
    }
}
