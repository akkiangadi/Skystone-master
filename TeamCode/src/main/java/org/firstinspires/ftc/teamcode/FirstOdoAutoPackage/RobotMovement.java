package org.firstinspires.ftc.teamcode.FirstOdoAutoPackage;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import java.util.ArrayList;

public class RobotMovement {

    DcMotor fl, fr, bl, br;

    FirstOdo odo = new FirstOdo(null, null, null);

    public void setEverything(DcMotor fl, DcMotor fr, DcMotor bl, DcMotor br, double globalX, double globalY){
        this.fl = fl;
        this.fr = fr;
        this.bl = bl;
        this.br = br;
        odo.odoReset(fl, fr, bl);
        odo.setGlobalX(globalX);
        odo.setGlobalY(globalY);
    }

    public RobotMovement(){

    }

    public FirstOdo getOdo(){
        return odo;
    }


    public void goToPosition2(double targetX, double targetY, double targetHeading, double movementSpeed, double turnSpeed){

        double deltaX = targetX - odo.getGlobalX();
        double deltaY = targetY - odo.getGlobalY();
        double deltaHeading = targetHeading - odo.getHeading();

        if (deltaX == 0){
            deltaX = 0.0001;
        } else if (deltaY == 0){
            deltaY = 0.0001;
        } else if (deltaHeading == 0){
            deltaY = 0.0001;
        }

        double snippedX = deltaX/(Math.abs(deltaX) + Math.abs(deltaY));
        double snippedY = deltaY/(Math.abs(deltaX) + Math.abs(deltaY));
        double snippedHeading = Range.clip(deltaHeading/(Math.toRadians(30)), -1, 1);

        double x2 = movementSpeed * (snippedX*Math.cos(odo.getHeading()) + snippedY*Math.sin(odo.getHeading()));
        double y2 = movementSpeed * (snippedY*Math.cos(odo.getHeading()) - snippedX*Math.sin(odo.getHeading()));
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

    public void goToPosition(double targetX, double targetY, double targetAngle, double moveSpeed, double turnSpeed){

        double distanceToTarget = Math.hypot(targetX-odo.getGlobalX(), targetY-odo.getGlobalY());
        double absoluteAngleToTarget = (Math.atan2(targetY-odo.getGlobalY(), targetX-odo.getGlobalX())) - Math.toRadians(180);

        double relativeAngleToPoint = AngleWrap(absoluteAngleToTarget - (odo.getHeading()));

        double deltaX = targetX - odo.getGlobalX();
        double deltaY = targetY - odo.getGlobalY();

        double movementXPower = deltaX/(Math.abs(deltaX) + Math.abs(deltaY));
        double movementYPower = deltaY/(Math.abs(deltaX) + Math.abs(deltaY));

        double movement_x = movementXPower * moveSpeed;
        double movement_y = movementYPower * moveSpeed;

        double relativeTurnAngle = relativeAngleToPoint + targetAngle + Math.toRadians(45);
        double movement_z = Range.clip(relativeTurnAngle/(Math.toRadians(30)), -1, 1) * turnSpeed;

        if (distanceToTarget < 10){
            movement_z = 0;
        }

        settPower(movement_x, movement_y, movement_z);

    }


    int tickerFollow = 0;
    public void followPath(ArrayList<Waypoint> allPoints){


        if (inTriggerZone(allPoints.get(tickerFollow))){
            tickerFollow+=1;
        }
        Waypoint targetPoint = allPoints.get(tickerFollow);
        goToPosition2(targetPoint.x(), targetPoint.y(), targetPoint.targetAngle(), targetPoint.moveSpeed(), targetPoint.turnSpeed());

    }

    public boolean inTriggerZone(Waypoint waypoint){
        boolean isIt;
        double distanceToTarget = Math.hypot(waypoint.x()-odo.getGlobalX(), waypoint.y()-odo.getGlobalY());

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
