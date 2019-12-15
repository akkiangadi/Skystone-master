package org.firstinspires.ftc.teamcode.FirstOdoAutoPackage;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import java.util.ArrayList;

public class RobotMovement {

    DcMotor fl, fr, bl, br;

    private double globalX, globalY, globalT;

    public void setEverything(DcMotor fl, DcMotor fr, DcMotor bl, DcMotor br){
        this.fl = fl;
        this.fr = fr;
        this.bl = bl;
        this.br = br;
    }

    public RobotMovement(){

    }

    public void movementUpdate(double globalX, double globalY, double globalT){
        this.globalT = globalT;
        this.globalX = globalX;
        this.globalY = globalY;
    }


    public void goToPosition2(double targetX, double targetY, double preferredHeading, double movementSpeed, double turnSpeed){

        if (preferredHeading == 400){
            preferredHeading = (Math.atan2(targetY- globalY, targetX-globalX)) - Math.toRadians(90);
        }

        double distanceToTarget = Math.hypot(targetX-globalX, targetY- globalY);
        double targetHeading;

        //optimizer
        if (distanceToTarget < 5){ //five is arbritary i dont know how to spell that word
            targetHeading = preferredHeading;
        } else {
            targetHeading = (Math.atan2(targetY- globalY, targetX-globalX)) - Math.toRadians(90);
        }

        //deltas for stuff later
        double deltaX = targetX - globalX;
        double deltaY = targetY - globalY;
        double deltaHeading = targetHeading - globalT;

        //this keeps everything from breaking DO NOT REMOVE IT
        if (deltaX == 0){
            deltaX = 0.0001;
        } else if (deltaY == 0){
            deltaY = 0.0001;
        } else if (deltaHeading == 0){
            deltaY = 0.0001;
        }

        //circ
        double snippedX = deltaX/(Math.abs(deltaX) + Math.abs(deltaY));
        double snippedY = deltaY/(Math.abs(deltaX) + Math.abs(deltaY));
        double snippedHeading = Range.clip(deltaHeading/(Math.toRadians(30)), -1, 1); //30 degrees is also arbitary still cant spell it

        double x2 = movementSpeed * (snippedX*Math.cos(globalT) + snippedY*Math.sin(globalT));
        double y2 = movementSpeed * (snippedY*Math.cos(globalT) - snippedX*Math.sin(globalT));
        double z2 = turnSpeed * snippedHeading;

        double flPower = x2 + y2 + z2;
        double frPower = x2 - y2 + z2;
        double blPower = -x2 + y2 + z2;
        double brPower = -x2 - y2 + z2;

        fl.setPower(flPower);
        fr.setPower(frPower);
        bl.setPower(blPower);
        br.setPower(brPower);
    }

    //old pp method don't use? idk
    public void goToPosition(double targetX, double targetY, double targetAngle, double moveSpeed, double turnSpeed){

        double distanceToTarget = Math.hypot(targetX-globalX, targetY- globalY);
        double absoluteAngleToTarget = (Math.atan2(targetY- globalY, targetX-globalX)) - Math.toRadians(90);

        double relativeAngleToPoint = AngleWrap(absoluteAngleToTarget - (globalT));

        double deltaX = targetX - globalX;
        double deltaY = targetY - globalY;

        double movementXPower = deltaX/(Math.abs(deltaX) + Math.abs(deltaY));
        double movementYPower = deltaY/(Math.abs(deltaX) + Math.abs(deltaY));

        double movement_x = movementXPower * moveSpeed;
        double movement_y = movementYPower * moveSpeed;

        double relativeTurnAngle = relativeAngleToPoint + targetAngle + Math.toRadians(90);
        double movement_z = Range.clip(relativeTurnAngle/(Math.toRadians(30)), -1, 1) * turnSpeed;

        if (distanceToTarget < 10){
            movement_z = 0;
        }

        settPower(movement_x, movement_y, movement_z);

    }


    int tickerFollow = 0;
    public boolean followPath(ArrayList<Waypoint> allPoints){

        if ((tickerFollow +1) < allPoints.size()){
            if (inTriggerZone(allPoints.get(tickerFollow))){
                tickerFollow+=1;
            }
            Waypoint targetPoint = allPoints.get(tickerFollow);
            goToPosition2(targetPoint.x(), targetPoint.y(), targetPoint.targetAngle(), targetPoint.moveSpeed(), targetPoint.turnSpeed());
            return true;
        } else {
            return false;
        }
    }

    public boolean inTriggerZone(Waypoint waypoint){
        boolean isIt;
        double distanceToTarget = Math.hypot(waypoint.x()-globalX, waypoint.y()-globalY);

        if (distanceToTarget < waypoint.triggerRadius()){
            isIt = true;
        } else {
            isIt = false;
        }
        return isIt;
    }

    public double AngleWrap(double angle){
        while(angle < -Math.PI){
            angle += Math.PI;
        }
        while(angle > Math.PI){
            angle -= Math.PI;
        }
        return angle;
    }

    public void settPower(double x_power, double y_power, double z_power){
        double flP = x_power + y_power + z_power;
        double frP = x_power - y_power + z_power;
        double blP = -x_power + y_power + z_power;
        double brP = -x_power - y_power + z_power;

        fl.setPower(flP);
        fr.setPower(frP);
        bl.setPower(blP);
        br.setPower(brP);
    }
}
