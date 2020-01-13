package org.firstinspires.ftc.teamcode.Yibbon.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {

    enum stateToggleIntake {
        intakeOn, intakeOff, intakeOut
    }

    private stateToggleIntake toggleIntake = stateToggleIntake.intakeOff;

    private boolean xgamepad, agamepad;
    private DcMotor i1, i2;
    private double time, timeIntakeToggle = 0;
    private HardwareMap hardwareMap;

    public Intake(){
    }

    public void init(HardwareMap hardwareMap){
        this.hardwareMap = hardwareMap;
        i1 = this.hardwareMap.dcMotor.get("i1");
        i2 = this.hardwareMap.dcMotor.get("i2");
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
                case intakeOut:
                    i1.setPower(-0.3);
                    i2.setPower(0.3);
                    if (agamepad){toggleIntake = stateToggleIntake.intakeOff;
                    timeIntakeToggle = time;}
                    if (xgamepad){toggleIntake = stateToggleIntake.intakeOn;
                    timeIntakeToggle = time;}
                    break;
                case intakeOff:
                    i1.setPower(0);
                    i2.setPower(0);
                    if (xgamepad){toggleIntake = stateToggleIntake.intakeOn;
                    timeIntakeToggle = time;}
                    if (agamepad){toggleIntake = stateToggleIntake.intakeOut;
                    timeIntakeToggle = time;}
                    break;
                case intakeOn:
                    i1.setPower(0.8);
                    i2.setPower(-0.8);
                    if (agamepad){toggleIntake = stateToggleIntake.intakeOut;
                    timeIntakeToggle = time;}
                    if (xgamepad){toggleIntake = stateToggleIntake.intakeOff;
                    timeIntakeToggle = time;}
                    break;
            }
        }
    }


}
