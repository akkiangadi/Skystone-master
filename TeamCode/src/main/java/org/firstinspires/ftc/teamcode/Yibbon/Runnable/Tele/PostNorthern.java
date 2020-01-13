package org.firstinspires.ftc.teamcode.Yibbon.Runnable.Tele;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Yibbon.RandomTelemetry;
import org.firstinspires.ftc.teamcode.Yibbon.Subsystems.AutoGrabbers;
import org.firstinspires.ftc.teamcode.Yibbon.Subsystems.Drivetrains.GyroDrive;
import org.firstinspires.ftc.teamcode.Yibbon.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Yibbon.Subsystems.Slides;
import org.firstinspires.ftc.teamcode.Yibbon.Subsystems.VirtualFourBar;
import org.firstinspires.ftc.teamcode.Yibbon.Subsystems.WaffleTrapper;

@TeleOp(name = "PostNorthern", group = "eee")
public class PostNorthern extends OpMode {

    GyroDrive gyroDrive = new GyroDrive(telemetry);
    Intake intake = new Intake();
    Slides slides = new Slides();
    WaffleTrapper waffleTrapper = new WaffleTrapper();
    VirtualFourBar v4b = new VirtualFourBar();
    AutoGrabbers autoGrab = new AutoGrabbers();
    ElapsedTime eTime = new ElapsedTime();
    String teleMessage = new String(RandomTelemetry.RandomTelemetry());

    @Override
    public void init() {
        gyroDrive.init(hardwareMap);
        intake.init(hardwareMap);
        slides.init(hardwareMap);
        waffleTrapper.init(hardwareMap);
        v4b.init(hardwareMap);
        autoGrab.init(hardwareMap);

        gyroDrive.resetEncoders();
        gyroDrive.runUsingEncoder();
        autoGrab.initPosition();
    }

    @Override
    public void loop() {
        gyroDrive.drivetrainInputs(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x, gamepad1.left_bumper, gamepad1.back, gamepad2.left_stick_button);
        intake.intakeInputs(gamepad1.x, gamepad1.y, eTime.time());
        slides.slideInputs2(gamepad1.dpad_up, gamepad1.dpad_down);
        waffleTrapper.waffleTrapperInputs(gamepad1.left_stick_button, eTime.time());
        v4b.v4bInputs(gamepad2.a, gamepad2.dpad_up, gamepad2.dpad_down, eTime.time());
        autoGrab.autoGrabInputs(gamepad2.left_bumper, gamepad2.right_bumper, gamepad2.left_trigger, gamepad2.right_trigger);
        telemetry.addData("OffsetAngle", gyroDrive.offsetAngle);
        telemetry.addData("", this.teleMessage);
    }
}