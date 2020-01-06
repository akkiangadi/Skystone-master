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
    public double offsetAngle = 0;
    boolean leftBumper, rightStickBut;
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


    public void drivetrainInputs(double leftStickXe, double leftStickYe, double rightStickXe, boolean leftBumpere, boolean rightStickButton){
        this.leftStickX = leftStickXe;
        this.rightStickX = rightStickXe;
        this.leftStickY = -leftStickYe;
        this.leftBumper = leftBumpere;
        this.rightStickBut = rightStickButton;
        weBeDrivin();
    }

    public void weBeDrivin(){
        this.angles   = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        this.pose = (Math.toRadians(angles.firstAngle) - this.offsetAngle);
        if (this.rightStickBut){
            this.offsetAngle = this.pose;

        }

        x2 = (this.leftStickX*Math.cos(pose) + this.leftStickY*Math.sin(pose));
        y2 = (this.leftStickY*Math.cos(pose) - this.leftStickX*Math.sin(pose));

        flPower = x2 + y2 + this.rightStickX;
        frPower = x2 - y2 + this.rightStickX;
        blPower = -x2 + y2 + this.rightStickX;
        brPower = -x2 - y2 + this.rightStickX;

        if (this.leftBumper == true){
            flPower/=2.5;
            frPower/=2.5;
            blPower/=2.5;
            brPower/=2.5;
        }

        fl.setPower(flPower);
        fr.setPower(frPower);
        bl.setPower(blPower);
        br.setPower(brPower);
    }

}
