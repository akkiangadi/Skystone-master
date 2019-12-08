package org.firstinspires.ftc.teamcode.Yibble.Runnable.Auto;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Yibble.BaseHardware;

public class ParkAuto extends OpMode {

    enum questionaire {
        question, red, blue, end
    }

    enum parkAutoPath {
        redLeft, redRight, blueLeft, blueRight
    }

    questionaire question = questionaire.question;
    parkAutoPath autoPath = parkAutoPath.redLeft;

    ElapsedTime eTime = new ElapsedTime();

    DcMotor fl, fr, bl, br;

    @Override
    public void init(){
        fl = hardwareMap.dcMotor.get("fl");
        bl = hardwareMap.dcMotor.get("bl");
        fr = hardwareMap.dcMotor.get("fr");
        br = hardwareMap.dcMotor.get("br");
    }

    @Override
    public void init_loop(){
        switch (question){
            case question:
                telemetry.addData("which team", "x for red, a for blue");
                if (eTime.time() > 0.1){
                    if (gamepad1.a){question = question.blue;}
                    if (gamepad1.x){question = question.red;}
                    eTime.reset();
                }
                break;
            case blue:
                telemetry.addData("which position in blue", "x for left, a for right");
                if (eTime.time() > 0.1){
                    if (gamepad1.a){autoPath = parkAutoPath.blueRight;}
                    if (gamepad1.x){autoPath = parkAutoPath.blueLeft;}
                    eTime.reset();
                }
                break;
            case red:
                telemetry.addData("which position in red", "x for left, a for right");
                if (eTime.time() > 0.1){
                    if (gamepad1.a){autoPath = parkAutoPath.redRight;}
                    if (gamepad1.x){autoPath = parkAutoPath.redLeft;}
                    eTime.reset();
                }
                break;
        }
    }

    @Override
    public void loop(){

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
    }/*
        switch (autoPath){
            case blueLeft:
                telemetry.addData("case", "blue left");
                gyroDriven().goForward(26);
                gyroDriven().goRight(21);
            case blueRight:
                telemetry.addData("case", "blue right");
                gyroDriven().goForward(26);
                gyroDriven().goRight(-21);
            case redRight:
                telemetry.addData("case", "blue right");
                gyroDriven().goForward(26);
                gyroDriven().goRight(-21);
            case redLeft:
                telemetry.addData("case", "blue right");
                gyroDriven().goForward(26);
                gyroDriven().goRight(21);
       */

}
