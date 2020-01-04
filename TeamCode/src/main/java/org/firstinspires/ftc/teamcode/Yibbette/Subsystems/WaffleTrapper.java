package org.firstinspires.ftc.teamcode.Yibbette.Subsystems;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class WaffleTrapper {

    Servo t1, t2;
    boolean dpadDown;
    ElapsedTime eTime = new ElapsedTime();

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

    public void waffleTrapperInputs (boolean button){
        this.dpadDown = button;
        intakeToggle();
    }

    public void intakeToggle(){
        switch (toggleWaffleTrapper){
            case trapperDown:
                t1.setPosition(1);
                t2.setPosition(0);
                if (eTime.time() > 0.2){
                    if (dpadDown){toggleWaffleTrapper = stateToggleWaffleTrapper.trapperUp;}
                    eTime.reset();
                }
                break;
            case trapperUp:
                t1.setPosition(0);
                t2.setPosition(1);
                if (eTime.time() > 0.2){
                    if (dpadDown){toggleWaffleTrapper = stateToggleWaffleTrapper.trapperDown;}
                    eTime.reset();
                }
                break;
            }

    }
}
