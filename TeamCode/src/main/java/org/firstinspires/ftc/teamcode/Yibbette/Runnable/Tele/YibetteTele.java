package org.firstinspires.ftc.teamcode.Yibbette.Runnable.Tele;

import android.transition.Slide;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Yibbette.Subsystems.Drivetrains.GyroDrive;
import org.firstinspires.ftc.teamcode.Yibbette.RandomTelemetry;
import org.firstinspires.ftc.teamcode.Yibbette.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Yibbette.Subsystems.Slides;
import org.firstinspires.ftc.teamcode.Yibbette.Subsystems.WaffleTrapper;

@TeleOp(name = "December30", group = "eee")
public class YibetteTele extends OpMode {


    DcMotor fl, fr, bl, br, i1, i2, s1, s2;
    Servo t1, t2, v1, v2, grab;
    BNO055IMU imu;

    GyroDrive gyroDrive = new GyroDrive(null, null, null, null, null);
    Intake intake = new Intake(null, null);
    Slides slides = new Slides(null, null);
    WaffleTrapper waffleTrapper = new WaffleTrapper(null, null);

    ElapsedTime eTime = new ElapsedTime();

    String teleMessage = new String(RandomTelemetry.RandomTelemetry());


    @Override
    public void init() {
        fl = hardwareMap.dcMotor.get("fl");
        bl = hardwareMap.dcMotor.get("bl");
        fr = hardwareMap.dcMotor.get("fr");
        br = hardwareMap.dcMotor.get("br");

        i1 = hardwareMap.dcMotor.get("i1");
        i2 = hardwareMap.dcMotor.get("i2");

        s1 = hardwareMap.dcMotor.get("sl");
        s2 = hardwareMap.dcMotor.get("sr");

        t1 = hardwareMap.servo.get("t1");
        t2 = hardwareMap.servo.get("t2");

        v1 = hardwareMap.servo.get("v1");
        v2 = hardwareMap.servo.get("v2");
        grab = hardwareMap.servo.get("grab");

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.calibrationDataFile = "AdafruitIMUCalibration.json";
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

        gyroDrive.assignGyroDrive(fl, fr, bl, br, imu);
        intake.assignIntake(i1, i2);
        slides.assignSlides(s1, s2);
        waffleTrapper.assignWaffleTrapper(t1, t2);

    }

    @Override
    public void loop() {
        gyroDrive.drivetrainInputs(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x, gamepad1.left_bumper, gamepad1.right_stick_button);
        intake.intakeInputs(gamepad1.x, gamepad1.y, eTime.time());
        slides.slideInputs(gamepad1.dpad_up, gamepad1.dpad_down);
        waffleTrapper.waffleTrapperInputs(gamepad1.left_stick_button, eTime.time());
        telemetry.addData("OffsetAngle", gyroDrive.offsetAngle);
        telemetry.addData("rightStickPress", gamepad1.right_stick_button);


        if (gamepad1.dpad_right){
            v1.setPosition(1);
            v2.setPosition(0);
        } else if (gamepad1.dpad_left){
            v1.setPosition(0);
            v2.setPosition(1);
        }

        if (gamepad1.right_bumper){
            grab.setPosition(1);
        } else if (gamepad1.right_trigger > 0.1){
            grab.setPosition(0);
        }

        telemetry.addData("fl pos: ", fl.getCurrentPosition());
        telemetry.addData("fr pos: ", fr.getCurrentPosition());
        telemetry.addData("bl pos: ", bl.getCurrentPosition());
        telemetry.addData("br pos: ", br.getCurrentPosition());
        telemetry.addData("slide pos: ", s1.getCurrentPosition());

        telemetry.addData("", teleMessage);
    }
}
