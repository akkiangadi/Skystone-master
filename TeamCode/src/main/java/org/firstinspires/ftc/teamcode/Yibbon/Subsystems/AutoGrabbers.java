package org.firstinspires.ftc.teamcode.Yibbon.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class AutoGrabbers{


    private Servo abgl1, abgl2, abgr1, abgr2;
    private boolean leftBump2, rightBump2;
    private double leftTrig2, rightTrig2;
    private HardwareMap hardwareMap;

    public AutoGrabbers(){
    }

    public void init(HardwareMap hardwareMap){
        this.hardwareMap = hardwareMap;
        abgl1 = this.hardwareMap.servo.get("l1");
        abgl2 = this.hardwareMap.servo.get("l2");
        abgr1 = this.hardwareMap.servo.get("r1");
        abgr2 = this.hardwareMap.servo.get("r2");
    }

    public void initPosition(){
        abgl1.setPosition(0.9);
        abgl2.setPosition(0.2);
        abgr1.setPosition(0.1);
        abgr2.setPosition(0.85);
    }

    public void autoGrabInputs(boolean leftBump2, boolean rightBump2, double leftTrig2, double rightTrig2){
        this.leftBump2 = leftBump2;
        this.rightBump2 = rightBump2;
        this.leftTrig2 = leftTrig2;
        this.rightTrig2 = rightTrig2;
        autoGrabbin();
    }

    private void autoGrabbin(){
        if (leftTrig2>0.1){
            abgl1.setPosition(0.22);
            abgr1.setPosition(0.82);
        } else if (rightTrig2>0.1){
            abgl1.setPosition(0.9);
            abgr1.setPosition(0.1);
        }

        if (leftBump2){
            abgl2.setPosition(0.1);
            abgr2.setPosition(0.9);
        } if (rightBump2){
            abgl2.setPosition(1);
            abgr2.setPosition(0);
        }
    }
}
