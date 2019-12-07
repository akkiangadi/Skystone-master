package org.firstinspires.ftc.teamcode.BasePurePursuit;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;


public class MyOpMode extends OpMode {

    @Override
    public void init() {

    }

    @Override
    public void loop() {
        RobotMovement.goToPosition(358.0, 358.0, 0.3);
    }
}
