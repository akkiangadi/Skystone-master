package org.firstinspires.ftc.teamcode.Yibbette.Subsystems;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class WaffleTrapper {

    Servo t1, t2;
    boolean leftButton;
    ElapsedTime eTimeWaffle = new ElapsedTime();

    enum stateToggleWaffleTrapper {
        trapperUp, trapperDown
    }

    stateToggleWaffleTrapper toggleWaffleTrapper = stateToggleWaffleTrapper.trapperUp;

    public WaffleTrapper (Servo t1e, Servo t2e){
        this.t1 = t1e;
        this.t2 = t2e;
    }

    public void assignWaffleTrapper (Servo t1e, Servo t2e){
        this.t1 = t1e;
        this.t2 = t2e;
    }

    public void waffleTrapperInputs (boolean leftStickButton){
        this.leftButton = leftStickButton;
        intakeToggle();
    }

    public void intakeToggle(){
        switch (toggleWaffleTrapper){
            case trapperDown:
                t1.setPosition(1);
                t2.setPosition(0);
                if (eTimeWaffle.time() > 0.2){
                    if (leftButton){toggleWaffleTrapper = stateToggleWaffleTrapper.trapperUp;}
                    eTimeWaffle.reset();
                }
                break;
            case trapperUp:
                t1.setPosition(0);
                t2.setPosition(1);
                if (eTimeWaffle.time() > 0.2){
                    if (leftButton){toggleWaffleTrapper = stateToggleWaffleTrapper.trapperDown;}
                    eTimeWaffle.reset();
                }
                break;
            }

    }
}
