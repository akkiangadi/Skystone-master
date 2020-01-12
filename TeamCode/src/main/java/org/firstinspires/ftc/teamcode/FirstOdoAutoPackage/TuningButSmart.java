package org.firstinspires.ftc.teamcode.FirstOdoAutoPackage;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

@TeleOp(name = "OdometryAutoTuningCauseYouFuckwadsDontGiveMeEnoughFuckingTime", group = "fuckyou")
public class TuningButSmart extends OpMode {
    private DcMotor fl, fr, bl, br, ol, or, h;
    private BNO055IMU imu;
    private double pose = 0, previouspose = 0;
    private Orientation angles;
    private ElapsedTime eTime = new ElapsedTime();
    private double widthOfEncoders = 0, finalencoders = 0;
    private double middleOffsetValue = 0, finalMiddleOffsetValue = 0;

    enum findTrackWidth {
        notThere, done
    }

    private findTrackWidth turnRobot = findTrackWidth.notThere;

    enum findMiddleOffset {
        notThere, done
    }

    private findMiddleOffset middleRobot = findMiddleOffset.done.notThere;


    @Override
    public void init() {
        fl = hardwareMap.dcMotor.get("fl");
        bl = hardwareMap.dcMotor.get("bl");
        fr = hardwareMap.dcMotor.get("fr");
        br = hardwareMap.dcMotor.get("br");
        ol = hardwareMap.dcMotor.get("i1");
        or = hardwareMap.dcMotor.get("i2");
        h = hardwareMap.dcMotor.get("sr");

        ol.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        or.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        h.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        ol.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        or.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        h.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.calibrationDataFile = "AdafruitIMUCalibration.json";
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
    }

    @Override
    public void loop() {

        if (gamepad1.dpad_right){
            fl.setPower(0.3);
            fr.setPower(0.3);
            bl.setPower(0.3);
            br.setPower(0.3);
        }

        this.angles   = imu.getAngularOrientation(AxesReference.INTRINSIC,
                AxesOrder.ZYX, AngleUnit.DEGREES);
        this.pose = (Math.toRadians(angles.firstAngle));

        widthCalc();
        middleWidthCalc();

    }

    private void widthCalc(){
        if (eTime.time() > 0.2){
            switch (turnRobot){
                case notThere:
                    if (gamepad1.a){turnRobot = findTrackWidth.done;
                        eTime.reset();
                        this.finalencoders = this.widthOfEncoders;}
                    telemetry.addData("heading and then press a", pose);
                    double temp = (ticksToInches(ol.getCurrentPosition()) - ticksToInches(or.getCurrentPosition()));
                    this.widthOfEncoders = (temp/pose);
                    telemetry.addData("Track Width", this.widthOfEncoders);
                    break;
                case done:
                    telemetry.addData("Track Width", this.finalencoders);
                    break;
            }
        }
    }

    private void middleWidthCalc(){
        if (eTime.time() > 0.2){
            switch (middleRobot){
                case notThere:
                    if (gamepad1.b){
                        middleRobot = findMiddleOffset.done;
                        eTime.reset();
                    }
                    break;
                case done:
                    telemetry.addData("turn 90", pose);
                    double inchestMiddle = ticksToInches(h.getCurrentPosition());
                    if (pose != 0){
                        this.middleOffsetValue = (inchestMiddle/pose);
                    } else {
                        this.middleOffsetValue = 0;
                    }
                    telemetry.addData("Middle Offset", this.middleOffsetValue);
                    break;
            }
        }
    }

    private double ticksToInches(int ticks){
        double DIAMETER_OF_ODO_WHEEL = 1.96;
        double inchesPerRotation = Math.PI*DIAMETER_OF_ODO_WHEEL;
        double ticksPerRotation = 8192;
        double conversionticksToInches = (inchesPerRotation/ticksPerRotation);
        return conversionticksToInches*ticks;
    }
}
