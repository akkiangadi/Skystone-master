package org.firstinspires.ftc.teamcode.Yibbon.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {

    enum stateToggleIntake {
        intakeOn, intakeOff, intakeOut
    }

    private stateToggleIntake toggleIntake = stateToggleIntake.intakeOff;

    private DcMotor i1, i2;
    private double time, timeIntakeToggle = 0;
    private HardwareMap hardwareMap;
    Gamepad gamepad1, gamepad2;

    public Intake(){
    }

    public void init(HardwareMap hardwareMap, Gamepad gamepad1, Gamepad gamepad2){
        this.hardwareMap = hardwareMap;
        this.gamepad1 = gamepad1;
        this.gamepad2 = gamepad2;
        i1 = this.hardwareMap.dcMotor.get("i1");
        i2 = this.hardwareMap.dcMotor.get("i2");
    }

    public void intakeInputs(double time){
        this.time = time;
        intakeToggle();
    }

    private void intakeToggle(){
        if (time > (timeIntakeToggle + 0.2)){
            switch (toggleIntake){
                case intakeOut:
                    i1.setPower(-0.3);
                    i2.setPower(0.3);
                    if (gamepad1.a){toggleIntake = stateToggleIntake.intakeOff;
                    timeIntakeToggle = time;}
                    if (gamepad1.x){toggleIntake = stateToggleIntake.intakeOn;
                    timeIntakeToggle = time;}
                    break;
                case intakeOff:
                    i1.setPower(0);
                    i2.setPower(0);
                    if (gamepad1.x){toggleIntake = stateToggleIntake.intakeOn;
                    timeIntakeToggle = time;}
                    if (gamepad1.a){toggleIntake = stateToggleIntake.intakeOut;
                    timeIntakeToggle = time;}
                    break;
                case intakeOn:
                    i1.setPower(0.8);
                    i2.setPower(-0.8);
                    if (gamepad1.a){toggleIntake = stateToggleIntake.intakeOut;
                    timeIntakeToggle = time;}
                    if (gamepad1.x){toggleIntake = stateToggleIntake.intakeOff;
                    timeIntakeToggle = time;}
                    break;
            }
        }
    }


}
