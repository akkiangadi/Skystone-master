package org.firstinspires.ftc.teamcode.Yibble.Runnable.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Yibble.RandomTelemetry;
import org.firstinspires.ftc.teamcode.Yibble.BaseHardware;

@TeleOp(name = "DecemberTest1", group = "vibers")
public class DecemberTest1 extends BaseHardware {

    String teleMessage = new String(RandomTelemetry.RandomTelemetry());

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void loop() {
        gyroDriven().drivetrainInputs(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x, gamepad1.left_bumper);
        //intaken().intakeInputs(gamepad1.x, gamepad1.a);
        waffleTrappern().waffleTrapperInputs(gamepad1.dpad_down);

        gyroDriven().weBeDrivin();
        //intaken().intakeToggle();
        waffleTrappern().intakeToggle();

        telemetry.addData("", teleMessage);
    }
}
