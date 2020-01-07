package org.firstinspires.ftc.teamcode.Yibbette.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Intake {

    enum stateToggleIntake {
        intakeOn, intakeOff, intakeOut
    }

    private stateToggleIntake toggleIntake = stateToggleIntake.intakeOff;

    private boolean xgamepad, agamepad;
    private DcMotor i1, i2;
    private double time, timeIntakeToggle = 0;

    public Intake(DcMotor i1e, DcMotor i2e){
        this.i1 = i1e;
        this.i2 = i2e;
    }

    public void assignIntake(DcMotor i1e, DcMotor i2e){
        this.i1 = i1e;
        this.i2 = i2e;
    }

    public void intakeInputs(boolean xgamepade, boolean agamepady, double time){
        this.agamepad = agamepady;
        this.xgamepad = xgamepade;
        this.time = time;
        intakeToggle();
    }

    private void intakeToggle(){
        if (time > (timeIntakeToggle + 0.2)){
            switch (toggleIntake){
                case intakeOn:
                    i1.setPower(-1);
                    i2.setPower(0.8);
                    if (xgamepad){toggleIntake = stateToggleIntake.intakeOff; timeIntakeToggle = time;}
                    if (agamepad){toggleIntake = stateToggleIntake.intakeOut; timeIntakeToggle = time;}
                    break;
                case intakeOff:
                    i1.setPower(0);
                    i2.setPower(0);
                    if (xgamepad){toggleIntake = stateToggleIntake.intakeOn; timeIntakeToggle = time;}
                    if (agamepad){toggleIntake = stateToggleIntake.intakeOut; timeIntakeToggle = time;}
                    break;
                case intakeOut:
                    i1.setPower(1);
                    i2.setPower(-1);
                    if (xgamepad){toggleIntake = stateToggleIntake.intakeOn; timeIntakeToggle = time;}
                    if (agamepad){toggleIntake = stateToggleIntake.intakeOff; timeIntakeToggle = time;}
                    break;
            }
        }

    }


}
