package org.firstinspires.ftc.teamcode.Yibbette.Subsystems.Drivetrains;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class GyroDrive {

    private DcMotor fl, fr, bl, br;
    double pose, leftStickX, leftStickY, rightStickX, x2, y2, flPower, frPower, blPower, brPower;
    boolean leftBumper;
    BNO055IMU imu;
    Orientation angles;



    public GyroDrive(DcMotor fle, DcMotor fre, DcMotor ble, DcMotor bre, BNO055IMU imuu){
        this.fl = fle;
        this.fr = fre;
        this.bl = ble;
        this.br = bre;
        this.imu = imuu;
    }

    public void assignGyroDrive(DcMotor fle, DcMotor fre, DcMotor ble, DcMotor bre, BNO055IMU imuu){
        this.fl = fle;
        this.fr = fre;
        this.bl = ble;
        this.br = bre;
        this.imu = imuu;
    }


    public void drivetrainInputs(double leftStickXe, double leftStickYe, double rightStickXe, boolean leftBumpere){
        this.leftStickX = leftStickXe;
        this.rightStickX = rightStickXe;
        this.leftStickY = -leftStickYe;
        this.leftBumper = leftBumpere;
    }

    public void weBeDrivin(){
        angles   = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        pose = Math.toRadians(angles.firstAngle);


        x2 = (this.leftStickX*Math.cos(pose) + this.leftStickY*Math.sin(pose));
        y2 = (this.leftStickY*Math.cos(pose) - this.leftStickX*Math.sin(pose));

        flPower = x2 + y2 + this.rightStickX;
        frPower = x2 - y2 + this.rightStickX;
        blPower = -x2 + y2 + this.rightStickX;
        brPower = -x2 - y2 + this.rightStickX;

        if (this.leftBumper == true){
            flPower/=5;
            frPower/=5;
            blPower/=5;
            brPower/=5;
        }

        fl.setPower(flPower);
        fr.setPower(frPower);
        bl.setPower(blPower);
        br.setPower(brPower);
    }

}
