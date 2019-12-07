package org.firstinspires.ftc.teamcode.BasePurePursuit;

public class MathFunctions {
    public static double AngleWrap(double angle){
        while(angle < -Math.PI){
            angle += Math.PI;
        }
        while(angle > Math.PI){
            angle -= Math.PI;
        }
        return angle;
    }
}
