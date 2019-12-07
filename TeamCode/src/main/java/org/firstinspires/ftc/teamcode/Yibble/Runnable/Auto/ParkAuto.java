package org.firstinspires.ftc.teamcode.Yibble.Runnable.Auto;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Yibble.BaseHardware;

public class ParkAuto extends BaseHardware {

    enum parkPosition {
        question, red, blue, end
    }

    enum parkAutoPath {
        redLeft, redRight, blueLeft, blueRight
    }

    parkPosition parkAutos = parkPosition.question;
    parkAutoPath autoPath = parkAutoPath.redLeft;

    ElapsedTime eTime = new ElapsedTime();

    @Override
    public void init(){
        autoInitFunction();
    }

    @Override
    public void init_loop(){
        switch (parkAutos){
            case question:
                telemetry.addData("which team", "x for red, a for blue");
                if (eTime.time() > 0.1){
                    if (gamepad1.a){parkAutos = parkPosition.blue;}
                    if (gamepad1.x){parkAutos = parkPosition.red;}
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
