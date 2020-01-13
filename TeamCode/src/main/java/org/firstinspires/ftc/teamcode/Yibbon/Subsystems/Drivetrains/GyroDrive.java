package org.firstinspires.ftc.teamcode.Yibbon.Subsystems.Drivetrains;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class GyroDrive {

    private DcMotor fl, fr, bl, br;
    double pose, leftStickX, leftStickY, rightStickX, x2, y2, flPower, frPower, blPower, brPower;
    public double offsetAngle = 0;
    boolean leftBumper, rightStickBut, leftStickBut2;
    BNO055IMU imu;
    Orientation angles;
    HardwareMap hardwareMap;
    Telemetry telemetry;
    boolean telemetryEnabled = false;

    public GyroDrive(Telemetry telemetry){
        this.telemetry = telemetry;
    }

    public void init(HardwareMap hardwareMap){
        this.hardwareMap = hardwareMap;
        fl = this.hardwareMap.dcMotor.get("fl");
        bl = this.hardwareMap.dcMotor.get("bl");
        fr = this.hardwareMap.dcMotor.get("fr");
        br = this.hardwareMap.dcMotor.get("br");

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.calibrationDataFile = "AdafruitIMUCalibration.json";
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        imu = this.hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
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

    private void driveMotorTelemetry(){
        telemetry.addData("fl Pos", fl.getCurrentPosition());
        telemetry.addData("fr Pos", fr.getCurrentPosition());
        telemetry.addData("bl Pos", bl.getCurrentPosition());
        telemetry.addData("br Pos", br.getCurrentPosition());
    }

    public void drivetrainInputs(double leftStickXe, double leftStickYe, double rightStickXe,
                                 boolean leftBumpere, boolean rightStickButton, boolean leftStickButton2){
        this.leftStickX = leftStickXe;
        this.rightStickX = rightStickXe;
        this.leftStickY = -leftStickYe;
        this.leftBumper = leftBumpere;
        this.rightStickBut = rightStickButton;
        this.leftStickBut2 = leftStickButton2;
        weBeDrivin();
        leftStickBut2EncoderReset();
    }

    public void enableTelemetry(){
        this.telemetryEnabled = true;
    }

    public void weBeDrivin(){
        this.angles   = imu.getAngularOrientation(AxesReference.INTRINSIC,
                AxesOrder.ZYX, AngleUnit.DEGREES);
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
            flPower/=3;
            frPower/=3;
            blPower/=3;
            brPower/=3;
        }

        fl.setPower(flPower);
        fr.setPower(frPower);
        bl.setPower(blPower);
        br.setPower(brPower);

        if (telemetryEnabled == true){
            driveMotorTelemetry();
        }
    }

}
