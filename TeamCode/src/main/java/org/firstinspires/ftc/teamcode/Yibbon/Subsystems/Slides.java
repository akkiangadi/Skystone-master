package org.firstinspires.ftc.teamcode.Yibbon.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Slides {

    private DcMotor s1, s2;
    HardwareMap hardwareMap;
    Gamepad gamepad1, gamepad2;
    boolean previousLeft, previousRight;

    public Slides(){
    }

    public void init(HardwareMap hardwareMap, Gamepad gamepad1, Gamepad gamepad2){
        this.hardwareMap = hardwareMap;
        this.gamepad1 = gamepad1;
        this.gamepad2 = gamepad2;
        this.s1 = hardwareMap.dcMotor.get("sl");
        this.s2 = hardwareMap.dcMotor.get("sr");
        s1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        s1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
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
            s1.setPower(-0.6);
            s2.setPower(0.6);
        } else {
            s1.setPower(0);
            s2.setPower(0);
        }
    }
    double SLIDE_MAX_HEIGHT = 5780, SLIDE_MIN_HEIGHT = 0;
    public void slideCheck2(){
        if (gamepad2.right_trigger > 0.1){
            if (gamepad2.right_bumper){
                s1.setPower(0.7);
                s2.setPower(-0.7);
            } else if (!gamepad2.right_bumper && previousRight){
                SLIDE_MAX_HEIGHT = s1.getCurrentPosition();
            }
            if (s1.getCurrentPosition() < SLIDE_MAX_HEIGHT){
                //up
                s1.setPower(0.7);
                s2.setPower(-0.7);
            }
        } else if (gamepad2.left_trigger > 0.1){
            if (gamepad2.left_bumper){
                s1.setPower(-0.5);
                s2.setPower(0.5);
            } else if (!gamepad2.left_bumper && previousLeft){
                SLIDE_MIN_HEIGHT = s1.getCurrentPosition();
            }
            if (s1.getCurrentPosition() > 0){
                //down
                s1.setPower(-0.5);
                s2.setPower(0.5);
            }
        } else {
            s1.setPower(0);
            s2.setPower(0);
        }
        bumperCheck();
    }

    public void bumperCheck(){
        previousLeft = gamepad1.left_bumper;
        previousRight = gamepad1.right_bumper;
    }
}
