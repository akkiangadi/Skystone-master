package org.firstinspires.ftc.teamcode.Yibble.Runnable.TeleOp;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;


@TeleOp(name = "field-oriented", group = "cruisecontrol")
@Disabled
public class FieldOriented extends OpMode {

    public DcMotor fl, fr, bl, br;
    BNO055IMU imu;
    Orientation angles;
    double x, y, x2, y2, z, pose;

    @Override
    public void init() {
        fl = hardwareMap.dcMotor.get("fl");
        bl = hardwareMap.dcMotor.get("bl");
        fr = hardwareMap.dcMotor.get("fr");
        br = hardwareMap.dcMotor.get("br");

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "AdafruitIMUCalibration.json";
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
    }

    @Override
    public void loop() {
        angles   = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        telemetry.addData("heading: ", angles.firstAngle);
        telemetry.addData("roll: ", angles.secondAngle);
        telemetry.addData("pitch: ", angles.thirdAngle);

        pose = Math.toRadians(angles.firstAngle);

        x = gamepad1.left_stick_x;
        y = -gamepad1.left_stick_y;
        z = gamepad1.right_stick_x;

        x2 = (x*Math.cos(pose) + y*Math.sin(pose));
        y2 = (y*Math.cos(pose) - x*Math.sin(pose));

        if (gamepad1.left_bumper){
            fl.setPower((x2 + y2 + z)/4);
            fr.setPower((x2 - y2 + z)/4);
            bl.setPower((-x2 + y2 + z)/4);
            br.setPower((-x2 - y2 + z)/4);
        } else {
            fl.setPower(x2 + y2 + z);
            fr.setPower(x2 - y2 + z);
            bl.setPower(-x2 + y2 + z);
            br.setPower(-x2 - y2 + z);
        }

    }
}
