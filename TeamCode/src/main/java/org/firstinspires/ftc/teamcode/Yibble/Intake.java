package org.firstinspires.ftc.teamcode.Yibble;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Intake {

    enum stateToggleIntake {
        intakeOn, intakeOff, intakeOut
    }

    stateToggleIntake toggleIntake = stateToggleIntake.intakeOff;

    boolean xgamepad, agamepad;
    DcMotor i1, i2;

    public Intake(DcMotor i1e, DcMotor i2e){
        this.i1 = i1e;
        this.i2 = i2e;
    }

    public void intakeAssign(DcMotor i1e, DcMotor i2e){
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
                if (xgamepad){toggleIntake = stateToggleIntake.intakeOff;}
                if (agamepad){toggleIntake = stateToggleIntake.intakeOut;}
                break;
            case intakeOff:
                i1.setPower(0);
                i2.setPower(0);
                if (xgamepad){toggleIntake = stateToggleIntake.intakeOn;}
                if (agamepad){toggleIntake = stateToggleIntake.intakeOut;}
                break;
            case intakeOut:
                i1.setPower(1);
                i2.setPower(-1);
                if (xgamepad){toggleIntake = stateToggleIntake.intakeOn;}
                if (agamepad){toggleIntake = stateToggleIntake.intakeOff;}
                break;
        }
    }


}
