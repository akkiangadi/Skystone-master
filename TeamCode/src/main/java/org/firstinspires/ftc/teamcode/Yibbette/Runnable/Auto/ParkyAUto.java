package org.firstinspires.ftc.teamcode.Yibbette.Runnable.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "ParkyAutoBad", group = "e")
public class ParkyAUto extends LinearOpMode{
    DcMotor fl, fr, bl, br;

    @Override
    public void runOpMode() throws InterruptedException {
        fl = hardwareMap.dcMotor.get("fl");
        bl = hardwareMap.dcMotor.get("bl");
        fr = hardwareMap.dcMotor.get("fr");
        br = hardwareMap.dcMotor.get("br");
        waitForStart();
        goForward(300);


    }

    private void goForward(int distance){
        motorReset();
        fl.setTargetPosition(distance);
        fr.setTargetPosition(distance);
        bl.setTargetPosition(-distance);
        br.setTargetPosition(-distance);
        powerBusy();
    }

    private void motorReset() {
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    private void powerBusy() {
        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
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
}
