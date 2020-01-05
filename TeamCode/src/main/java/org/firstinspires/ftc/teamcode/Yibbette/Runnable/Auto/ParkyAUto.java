package org.firstinspires.ftc.teamcode.Yibbette.Runnable.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "ParkyAutoBad", group = "e")
public class ParkyAUto extends OpMode {
    DcMotor fl, fr, bl, br;

    @Override
    public void init() {
        fl = hardwareMap.dcMotor.get("fl");
        bl = hardwareMap.dcMotor.get("bl");
        fr = hardwareMap.dcMotor.get("fr");
        br = hardwareMap.dcMotor.get("br");
    }

    @Override
    public void loop() {

    }
}
