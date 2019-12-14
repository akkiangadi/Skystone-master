package org.firstinspires.ftc.teamcode.FirstOdoAutoPackage;

public class Waypoint {

    private double xPoint, yPoint, triggerRadius, moveSpeed, turnSpeed;

    public Waypoint(double x, double y, double triggerRadius, double moveSpeed, double turnSpeed){
        this.xPoint = x;
        this.yPoint = y;
        this.triggerRadius = triggerRadius;
        this.moveSpeed = moveSpeed;
        this.turnSpeed = turnSpeed;
    }

    public double x(){
        return xPoint;
    }

    public double y(){
        return yPoint;
    }

    public double triggerRadius(){
        return triggerRadius;
    }

    public double moveSpeed(){
        return moveSpeed;
    }

    public double turnSpeed(){
        return turnSpeed;
    }

}
