package org.firstinspires.ftc.teamcode.Yibbon.Subsystems;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class AutoGrabbers{


    private Servo abgl1, abgl2, abgr1, abgr2;
    private HardwareMap hardwareMap;
    Gamepad gamepad1, gamepad2;


    public AutoGrabbers(){
    }

    public void init(HardwareMap hardwareMap, Gamepad gamepad1, Gamepad gamepad2, boolean initPositionBool){
        this.gamepad1 = gamepad1;
        this.gamepad2 = gamepad2;
        this.hardwareMap = hardwareMap;
        abgl1 = this.hardwareMap.servo.get("l1");
        abgl2 = this.hardwareMap.servo.get("l2");
        abgr1 = this.hardwareMap.servo.get("r1");
        abgr2 = this.hardwareMap.servo.get("r2");
        if (initPositionBool){
            initPosition();
        }
    }

    public void initPosition(){
        abgl1.setPosition(0.9);
        abgl2.setPosition(0.2);
        abgr1.setPosition(0.1);
        abgr2.setPosition(0.85);
    }

    public void autoGrabInputs(){
        autoGrabbin();
    }

    private void autoGrabbin(){
        if (gamepad2.left_trigger>0.1){
            abgl1.setPosition(0.22);
            abgr1.setPosition(0.82);
        } else if (gamepad2.right_trigger>0.1){
            abgl1.setPosition(0.9);
            abgr1.setPosition(0.1);
        }

        if (gamepad2.left_bumper){
            abgl2.setPosition(0.1);
            abgr2.setPosition(0.9);
        } else if (gamepad2.right_bumper){
            abgl2.setPosition(1);
            abgr2.setPosition(0);
        }
    }
}
