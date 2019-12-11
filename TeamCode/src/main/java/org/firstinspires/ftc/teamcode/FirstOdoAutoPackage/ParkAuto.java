package org.firstinspires.ftc.teamcode.FirstOdoAutoPackage;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name = "ParkAuto", group = "Workers")
public class ParkAuto extends OpMode {
    DcMotor fl, fr, bl, br;
    StaticOdo odo = new StaticOdo(null, null, null);

    @Override
    public void init() {
        fl = hardwareMap.dcMotor.get("fl");
        bl = hardwareMap.dcMotor.get("bl");
        fr = hardwareMap.dcMotor.get("fr");
        br = hardwareMap.dcMotor.get("br");

        odo.odoReset(fl, fr, bl);
        odo.setGlobalX(0);
        odo.setGlobalY(0);
    }

    @Override
    public void loop() {
        odo.odoloop();
        goToPosition(-100, 100, 45, 0.6, 0.3);

    }

    public void goToPosition(double targetX, double targetY, double targetAngle, double moveSpeed, double turnSpeed){

        double distanceToTarget = Math.hypot(targetX-odo.getGlobalX(), targetY-odo.getGlobalY());
        double absoluteAngleToTarget = Math.atan2(targetY-odo.getGlobalY(), targetX-odo.getGlobalY());

        double relativeAngleToPoint = AngleWrap(absoluteAngleToTarget - (odo.getHeading()- Math.toRadians(90)));

        double deltaX = targetX - odo.getGlobalX();
        double deltaY = targetY - odo.getGlobalY();

        double movementXPower = deltaX/(Math.abs(deltaX) + Math.abs(deltaY));
        double movementYPower = deltaY/(Math.abs(deltaX) + Math.abs(deltaY));

        double movement_x = movementXPower * moveSpeed;
        double movement_y = movementYPower * moveSpeed;

        double relativeTurnAngle = relativeAngleToPoint - Math.toRadians(180) + targetAngle;
        double movement_z = Range.clip(relativeTurnAngle/(Math.toRadians(30)), -1, 1) * turnSpeed;

        if (distanceToTarget < 10){
            movement_z = 0;
        }

        settPower(movement_x, movement_y, movement_z);

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
