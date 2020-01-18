package org.firstinspires.ftc.teamcode.Yibbon.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Slides {

    private DcMotor s1, s2;
    HardwareMap hardwareMap;
    Gamepad gamepad1, gamepad2;

    public Slides(){
    }

    public void init(HardwareMap hardwareMap, Gamepad gamepad1, Gamepad gamepad2){
        this.hardwareMap = hardwareMap;
        this.gamepad1 = gamepad1;
        this.gamepad2 = gamepad2;
        s1 = hardwareMap.dcMotor.get("sl");
        s2 = hardwareMap.dcMotor.get("sr");
    }

    public void slideInputs(){
        slideCheck();
    }

    public void slideInputs2(){
        slideCheck2();
    }

    public void slideCheck(){
        if (gamepad1.dpad_up){
            if (s1.getCurrentPosition() < 5782){
                s1.setPower(1);
                s2.setPower(-1);
            }
        } else if (gamepad1.dpad_down){
            s1.setPower(-0.5);
            s2.setPower(0.5);
        } else {
            s1.setPower(0);
            s2.setPower(0);
        }
    }

    public void slideCheck2(){
        if (gamepad1.dpad_up){
            if (s1.getCurrentPosition() < 5780){
                s1.setPower(0.7);
                s2.setPower(-0.7);
            }
        } else if (gamepad1.dpad_down){
            if (s1.getCurrentPosition() > 0){
                s1.setPower(-0.3);
                s2.setPower(0.3);
            }
        } else {
            s1.setPower(0);
            s2.setPower(0);
        }
    }
}
