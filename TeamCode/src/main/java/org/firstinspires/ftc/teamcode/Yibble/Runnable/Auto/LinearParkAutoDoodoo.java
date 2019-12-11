package org.firstinspires.ftc.teamcode.Yibble.Runnable.Auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class LinearParkAutoDoodoo extends LinearOpMode {
    DcMotor fl, fr, bl, br;

    @Override
    public void runOpMode() throws InterruptedException {
        fl = hardwareMap.dcMotor.get("fl");
        bl = hardwareMap.dcMotor.get("bl");
        fr = hardwareMap.dcMotor.get("fr");
        br = hardwareMap.dcMotor.get("br");

        waitForStart();
        while (opModeIsActive()){

        }
    }

    private void motorReset() {
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    private void powerBusy() {
        fl.setPower(0.65);
        fr.setPower(0.65);
        bl.setPower(0.65);
        br.setPower(0.65);
        while ((bl.isBusy() && br.isBusy()) && (fl.isBusy() && fr.isBusy())){}
        bl.setPower(0);
        fr.setPower(0);
        fl.setPower(0);
        br.setPower(0);
    }

    public void goForward(int gofron){
        int tickCalc = inchesToTicks(gofron);
        motorReset();
        fl.setTargetPosition(tickCalc);
        fr.setTargetPosition(-tickCalc);
        br.setTargetPosition(-tickCalc);
        bl.setTargetPosition(tickCalc);
        powerBusy();
    }

    public void goRight(int gofron){
        int tickCalc = inchesToTicks(gofron);
        motorReset();
        fl.setTargetPosition(tickCalc);
        fr.setTargetPosition(tickCalc);
        br.setTargetPosition(-tickCalc);
        bl.setTargetPosition(-tickCalc);
        powerBusy();
    }

    private int inchesToTicks(int inchy){
        int ticksPerRotation = 672;
        double wheelDiameter = 2.95;
        double inchesPerRotation = wheelDiameter* Math.PI;
        double ticksPerInch = ticksPerRotation/inchesPerRotation;
        int ticksy = (int) Math.round(ticksPerInch*inchy);
        return ticksy;
    }
}