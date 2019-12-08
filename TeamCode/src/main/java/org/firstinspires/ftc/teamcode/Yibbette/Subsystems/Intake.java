package org.firstinspires.ftc.teamcode.Yibbette.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Intake {

    enum stateToggleIntake {
        intakeOn, intakeOff, intakeOut
    }

    stateToggleIntake toggleIntake = stateToggleIntake.intakeOff;

    boolean xgamepad, agamepad;
    DcMotor i1, i2;
    ElapsedTime eTime2 = new ElapsedTime();

    public Intake(DcMotor i1e, DcMotor i2e){
        this.i1 = i1e;
        this.i2 = i2e;
    }

    public void assignIntake(DcMotor i1e, DcMotor i2e){
        this.i1 = i1e;
        this.i2 = i2e;
    }

    public void intakeInputs(boolean xgamepade, boolean agamepady){
        this.agamepad = agamepady;
        this.xgamepad = xgamepade;
    }

    public void intakeToggle(){
        switch (toggleIntake){
            case intakeOn:
                i1.setPower(-1);
                i2.setPower(0.8);
                if (eTime2.time() > 0.2){
                    if (xgamepad){toggleIntake = stateToggleIntake.intakeOff;}
                    if (agamepad){toggleIntake = stateToggleIntake.intakeOut;}
                    eTime2.reset();
                }
                break;
            case intakeOff:
                i1.setPower(0);
                i2.setPower(0);
                if (eTime2.time() > 0.2){
                    if (xgamepad){toggleIntake = stateToggleIntake.intakeOn;}
                    if (agamepad){toggleIntake = stateToggleIntake.intakeOut;}
                    eTime2.reset();
                }
                break;
            case intakeOut:
                i1.setPower(1);
                i2.setPower(-1);
                if (eTime2.time() > 0.2){
                    if (xgamepad){toggleIntake = stateToggleIntake.intakeOn;}
                    if (agamepad){toggleIntake = stateToggleIntake.intakeOff;}
                    eTime2.reset();
                }
                break;
        }
    }


}
