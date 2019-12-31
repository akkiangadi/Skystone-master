package org.firstinspires.ftc.teamcode.Yibbette.Subsystems;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Slides {

    boolean dup, ddown;
    DcMotor s1, s2;
    ElapsedTime eTime2 = new ElapsedTime();

    public Slides(DcMotor s1, DcMotor s2){
        this.s1 = s1;
        this.s2 = s2;
    }

    public void assignSlides(DcMotor s1, DcMotor s2){
        this.s1 = s1;
        this.s2 = s2;
    }

    public void slideInputs(boolean dup, boolean ddown){
        this.dup = dup;
        this.ddown = ddown;
    }

    public void slideCheck(){
        if (dup){
            s1.setPower(1);
            s2.setPower(-1);
        } else if (ddown){
            s1.setPower(-1);
            s2.setPower(1);
        } else {
            s1.setPower(0);
            s2.setPower(0);
        }
    }
}
