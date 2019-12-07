package org.firstinspires.ftc.teamcode.Yibble.Runnable.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Yibble.RandomTelemetry;
import org.firstinspires.ftc.teamcode.Yibble.BaseHardware;

import java.util.Random;

@TeleOp(name = "TruePain", group = "groupy")
public class TruePain extends BaseHardware {

    String teleMessage = new String(RandomTelemetry.RandomTelemetry());
    Random rando = new Random();
    int chooser;
    String[] dtList = {"gyro", "regular", "pain"};
    String dtChosen;
    ElapsedTime time = new ElapsedTime();

    @Override
    public void init() {
    }

    @Override
    public void loop() {
        if (time.time()>10){
            chooser = rando.nextInt(3);
            this.dtChosen = dtList[chooser];
            if (this.dtChosen == "gyro"){
                gyroInitFunction();
            } else if (this.dtChosen == "regular"){
                regularInitFunction();
            } else if (this.dtChosen == "pain"){
                painInitFunction();
            }
            time.reset();
        }

        if (this.dtChosen == "gyro"){
            gyroDriven().drivetrainInputs(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x, gamepad1.left_bumper);
            gyroDriven().weBeDrivin();
            telemetry.addData("", "field oriented");
        }else if (this.dtChosen == "regular"){
            regularDriven().drivetrainInputs(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
            regularDriven().weBeDrivin();
            telemetry.addData("", "robot oriented");
        }else if (this.dtChosen == "pain"){
            painDriven().drivetrainInputs(gamepad1.right_stick_x, gamepad1.left_trigger, gamepad1.right_trigger, gamepad1.left_bumper);
            painDriven().weBeCryin();
            telemetry.addData("", "fuck you");

        }
        telemetry.addData("", teleMessage);
    }
}
