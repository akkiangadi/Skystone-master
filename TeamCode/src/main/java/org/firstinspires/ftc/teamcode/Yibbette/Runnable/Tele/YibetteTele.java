package org.firstinspires.ftc.teamcode.Yibbette.Runnable.Tele;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Yibbette.Subsystems.Drivetrains.GyroDrive;
import org.firstinspires.ftc.teamcode.Yibbette.RandomTelemetry;
import org.firstinspires.ftc.teamcode.Yibbette.Subsystems.WaffleTrapper;

public class YibetteTele extends OpMode {


    DcMotor fl, fr, bl, br, i1, i2;
    Servo t1, t2;
    BNO055IMU imu;

    GyroDrive gyroDrive = new GyroDrive(null, null, null, null, null);
    //Intake intake = new Intake(null, null);
    WaffleTrapper waffleTrapper = new WaffleTrapper(null, null);

    String teleMessage = new String(RandomTelemetry.RandomTelemetry());


    @Override
    public void init() {
        fl = hardwareMap.dcMotor.get("fl");
        bl = hardwareMap.dcMotor.get("bl");
        fr = hardwareMap.dcMotor.get("fr");
        br = hardwareMap.dcMotor.get("br");

        //i1 = hardwareMap.dcMotor.get("i1");
        //i2 = hardwareMap.dcMotor.get("i2");

        t1 = hardwareMap.servo.get("t1");
        t2 = hardwareMap.servo.get("t2");

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.calibrationDataFile = "AdafruitIMUCalibration.json";
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

        gyroDrive.assignGyroDrive(fl, fr, bl, br, imu);
        //intake.assignIntake(i1, i2);
        waffleTrapper.assignWaffleTrapper(t1, t2);

    }

    @Override
    public void loop() {
        gyroDrive.drivetrainInputs(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x, gamepad1.left_bumper);
        //intake.intakeInputs(gamepad1.x, gamepad1.y);
        waffleTrapper.waffleTrapperInputs(gamepad1.dpad_down);


        gyroDrive.weBeDrivin();
        //intake.intakeToggle();
        waffleTrapper.intakeToggle();

        telemetry.addData("", teleMessage);
    }
}