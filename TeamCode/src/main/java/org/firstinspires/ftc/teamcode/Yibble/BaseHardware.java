package org.firstinspires.ftc.teamcode.Yibble;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Yibble.Drivetrains.AutoDT;
import org.firstinspires.ftc.teamcode.Yibble.Drivetrains.GyroDrive;
import org.firstinspires.ftc.teamcode.Yibble.Drivetrains.PainDrive;
import org.firstinspires.ftc.teamcode.Yibble.Drivetrains.RobotDrive;

public class BaseHardware extends OpMode{
    DcMotor fl, fr, bl, br, i1, i2;
    Servo t1, t2;
    BNO055IMU imu;

    GyroDrive gyrodriven = new GyroDrive(null, null, null, null, null);
    RobotDrive robodriven = new RobotDrive(null, null, null, null);
    PainDrive paindriven = new PainDrive(null, null, null, null);
    AutoDT autoDT = new AutoDT(null, null, null, null);

    Intake intake = new Intake(null, null);
    WaffleTrapper waffle = new WaffleTrapper(null, null);

    public GyroDrive gyroDriven(){
        return gyrodriven;
    }

    public Intake intaken(){
        return intake;
    }

    public RobotDrive regularDriven(){
        return robodriven;
    }

    public PainDrive painDriven(){
        return paindriven;
    }

    public AutoDT autoDTen(){ return autoDT; }

    public WaffleTrapper waffleTrappern(){
        return waffle;
    }

    public void gyroInitFunction(){
        fl = hardwareMap.dcMotor.get("fl");
        bl = hardwareMap.dcMotor.get("bl");
        fr = hardwareMap.dcMotor.get("fr");
        br = hardwareMap.dcMotor.get("br");

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.calibrationDataFile = "AdafruitIMUCalibration.json";
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

        gyrodriven.GyroDriveAssign(fl, fr, bl, br, imu);
    }

    public void regularInitFunction(){
        fl = hardwareMap.dcMotor.get("fl");
        bl = hardwareMap.dcMotor.get("bl");
        fr = hardwareMap.dcMotor.get("fr");
        br = hardwareMap.dcMotor.get("br");

        robodriven.RobotDriveAssign(fl, fr, bl, br);
    }

    public void autoInitFunction(){
        fl = hardwareMap.dcMotor.get("fl");
        bl = hardwareMap.dcMotor.get("bl");
        fr = hardwareMap.dcMotor.get("fr");
        br = hardwareMap.dcMotor.get("br");

        robodriven.RobotDriveAssign(fl, fr, bl, br);
    }

    public void painInitFunction(){
        fl = hardwareMap.dcMotor.get("fl");
        bl = hardwareMap.dcMotor.get("bl");
        fr = hardwareMap.dcMotor.get("fr");
        br = hardwareMap.dcMotor.get("br");

        paindriven.PainDriveAssign(fl, fr, bl, br);
    }

    public void intakeInitFunction(){
        i1 = hardwareMap.dcMotor.get("i1");
        i2 = hardwareMap.dcMotor.get("i2");

        intake.intakeAssign(i1, i2);
    }

    public void waffleTrapperInitFunction(){
        t1 = hardwareMap.servo.get("t1");
        t2 = hardwareMap.servo.get("t2");

        waffle.waffleTrapperAssign(t1, t2);
    }

    public void init() {
        gyroInitFunction();
        //intakeInitFunction();
        waffleTrapperInitFunction();

        //regularInitFunction();
        //painInitFunction();
    }

    public void loop() {

    }

    public void init_loop(){

    }

    public void stop(){

    }

    public void start(){

    }
}
