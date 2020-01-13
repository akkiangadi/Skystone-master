package org.firstinspires.ftc.teamcode.Yibbon.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class VirtualFourBar {

    enum stateToggleGrab {
        grab, notgrab
    }

    private Servo grab, v1, v2;
    private stateToggleGrab toggleGrab = stateToggleGrab.notgrab;
    private double time, timeGrabToggle = 0;
    private boolean a2, dpadup2, dpaddown2;
    private HardwareMap hardwareMap;

    public VirtualFourBar(){
    }

    public void init(HardwareMap hardwareMap){
        this.hardwareMap = hardwareMap;
        v1 = this.hardwareMap.servo.get("v1");
        v2 = this.hardwareMap.servo.get("v2");
        grab = this.hardwareMap.servo.get("grab");
    }

    public void v4bInputs(boolean a2, boolean dpadup2, boolean dpaddown2, double time){
        this.a2 = a2;
        this.dpaddown2 = dpaddown2;
        this.dpadup2 = dpadup2;
        this.time = time;
        grabToggle();
        v4b();
    }

    private void v4b(){
        if (dpadup2){
            v1.setPosition(0.7);
            v2.setPosition(0.3);
        } else if (dpaddown2){
            v1.setPosition(0.3);
            v2.setPosition(0.7);
        } else {
            v1.setPosition(0.5);
            v2.setPosition(0.5);
        }
    }

    private void grabToggle(){
        if (time > (timeGrabToggle + 0.2)){
            switch (toggleGrab){
                case notgrab:
                    grab.setPosition(0.7);
                    if (a2){toggleGrab = stateToggleGrab.grab;
                        timeGrabToggle = time;}
                    break;
                case grab:
                    grab.setPosition(0.3);
                    if (a2){toggleGrab = stateToggleGrab.notgrab;
                        timeGrabToggle = time;}
                    break;
            }
        }
    }
}
