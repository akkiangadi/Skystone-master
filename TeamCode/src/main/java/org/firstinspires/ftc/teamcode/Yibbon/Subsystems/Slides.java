package org.firstinspires.ftc.teamcode.Yibbon.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Slides {

    private boolean dup, ddown;
    private DcMotor s1, s2;
    HardwareMap hardwareMap;

    public Slides(){
    }

    public void init(HardwareMap hardwareMap){
        this.hardwareMap = hardwareMap;
        s1 = hardwareMap.dcMotor.get("sl");
        s2 = hardwareMap.dcMotor.get("sr");
    }

    public void slideInputs(boolean dup, boolean ddown){
        this.dup = dup;
        this.ddown = ddown;
        slideCheck();
    }

    public void slideInputs2(boolean dup, boolean ddown){
        this.dup = dup;
        this.ddown = ddown;
        slideCheck2();
    }

    public void slideCheck(){
        if (dup){
            if (s1.getCurrentPosition() < 5782){
                s1.setPower(1);
                s2.setPower(-1);
            }
        } else if (ddown){
            s1.setPower(-0.5);
            s2.setPower(0.5);
        } else {
            s1.setPower(0);
            s2.setPower(0);
        }
    }

    public void slideCheck2(){
        if (dup){
            if (s1.getCurrentPosition() < 5780){
                s1.setPower(0.7);
                s2.setPower(-0.7);
            }
        } else if (ddown){
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
