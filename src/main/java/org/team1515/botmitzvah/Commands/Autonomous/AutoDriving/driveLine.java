package org.team1515.botmitzvah.Commands.Autonomous.AutoDriving;

import edu.wpi.first.wpilibj2.command.CommandBase;

import org.team1515.botmitzvah.Subsystems.Drivetrain;

public class driveLine extends CommandBase {
    private final Drivetrain drivetrain;
    private double i; //i vector component
    private double j; //j vector component
    private double ti; //initial time parameter
    private double tf; //final time parameter
    private double realTime; //actial system time
    public driveLine(Drivetrain drivetrain, double i double j, double ti, double tf) {
        this.drivetrain = drivetrain;
        this.i = i;
        this.j = j;
        this.ti = ti;
        this.tf = tf;
        realTime = System.currentTimeMillis();
    }

    @Override
    public void execute() {
        drivetrain.drive(i,j,true,true)
    }

    @Override
    public boolean isFinished() {
        return System.currentTimeMillis()-realTime >= tf-ti
    }

    @Override
    public void end(boolean interrupted) {
        drivetrainSubsystem.drive(new Translation2d(0.0, 0.0), 0.0, false, false);
    }
}