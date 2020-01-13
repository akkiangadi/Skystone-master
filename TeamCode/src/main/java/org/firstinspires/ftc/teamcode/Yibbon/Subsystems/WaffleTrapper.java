package org.firstinspires.ftc.teamcode.Yibbon.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class WaffleTrapper {

    private Servo t1, t2;
    private boolean leftButton;
    private double time, timeToggleWaffle = 0;
    private HardwareMap hardwareMap;
    enum stateToggleWaffleTrapper {
        trapperUp, trapperDown
    }

    stateToggleWaffleTrapper toggleWaffleTrapper = stateToggleWaffleTrapper.trapperUp;

    public WaffleTrapper (){
    }

    public void init(HardwareMap hardwareMap){
        this.hardwareMap = hardwareMap;
        t1 = this.hardwareMap.servo.get("t1");
        t2 = this.hardwareMap.servo.get("t2");
    }


    public void waffleTrapperInputs (boolean leftStickButton, double time){
        this.leftButton = leftStickButton;
        this.time = time;
        waffleToggle();
    }

    public void waffleToggle(){
        if (time > (timeToggleWaffle + 0.2)){
            switch (toggleWaffleTrapper){
                case trapperDown:
                    t1.setPosition(1);
                    t2.setPosition(0);
                    if (leftButton){toggleWaffleTrapper = stateToggleWaffleTrapper.trapperUp; timeToggleWaffle = time;}
                    break;
                case trapperUp:
                    t1.setPosition(0);
                    t2.setPosition(1);
                    if (leftButton){toggleWaffleTrapper = stateToggleWaffleTrapper.trapperDown; timeToggleWaffle = time;}
                    break;
            }
        }
    }
}
