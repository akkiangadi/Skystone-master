package org.firstinspires.ftc.teamcode.Yibbon.Subsystems.Drivetrains;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class GyroDrive {

    private DcMotor fl, fr, bl, br;
    double pose, x2, y2, flPower, frPower, blPower, brPower;
    public double offsetAngle = 0;
    private double time, timeSlowReset = 0;
    BNO055IMU imu;
    Orientation angles;
    HardwareMap hardwareMap;
    Gamepad gamepad1, gamepad2;
    boolean telemetryEnabled = false;

    public GyroDrive(){ }

    public void init(HardwareMap hardwareMap, Gamepad gamepad1, Gamepad gamepad2, boolean encoderBool){
        this.gamepad1 = gamepad1;
        this.gamepad2 = gamepad2;
        this.hardwareMap = hardwareMap;
        fl = this.hardwareMap.dcMotor.get("fl");
        bl = this.hardwareMap.dcMotor.get("bl");
        fr = this.hardwareMap.dcMotor.get("fr");
        br = this.hardwareMap.dcMotor.get("br");

        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.calibrationDataFile = "AdafruitIMUCalibration.json";
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        imu = this.hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
        /*
        if (encoderBool){
            resetEncoders();
            RunUsingEncoder();
        }
         */
        resetEncoders();
        runUsingEncoder();
    }

    public void resetEncoders(){
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void runUsingEncoder(){
        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void runWithoutEncoders(){
        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void leftStickBut2EncoderReset(){
        resetEncoders();
        runUsingEncoder();
    }

    public void drivetrainInputs(double time){
        this.time = time;
        weBeDrivin();
        //leftStickBut2EncoderReset();
    }

    public void enableTelemetry(){
        this.telemetryEnabled = true;
    }

    public void weBeDrivin(){
        this.angles   = imu.getAngularOrientation(AxesReference.INTRINSIC,
                AxesOrder.ZYX, AngleUnit.DEGREES);
        this.pose = (Math.toRadians(angles.firstAngle) - this.offsetAngle);
        if (time > (timeSlowReset + 0.2)){
            if (this.gamepad1.back){
                this.offsetAngle = this.pose;
                timeSlowReset = time;
            }
        }
        double yy = this.gamepad1.left_stick_y;

        x2 = (this.gamepad1.left_stick_x*Math.cos(pose) + (-yy)*Math.sin(pose));
        y2 = ((-yy)*Math.cos(pose) - this.gamepad1.left_stick_x*Math.sin(pose));

        flPower = x2 + y2 + this.gamepad1.right_stick_x;
        frPower = x2 - y2 + this.gamepad1.right_stick_x;
        blPower = -x2 + y2 + this.gamepad1.right_stick_x;
        brPower = -x2 - y2 + this.gamepad1.right_stick_x;

        if (this.gamepad1.left_bumper == true){
            flPower/=4;
            frPower/=4;
            blPower/=4;
            brPower/=4;
        }

        fl.setPower(flPower);
        fr.setPower(frPower);
        bl.setPower(blPower);
        br.setPower(brPower);

    }

}
