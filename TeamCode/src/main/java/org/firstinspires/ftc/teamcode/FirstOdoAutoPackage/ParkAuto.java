package org.firstinspires.ftc.teamcode.FirstOdoAutoPackage;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.ArrayList;

@Autonomous(name = "ParkAuto", group = "Workers")
public class ParkAuto extends OpMode {

    DcMotor fl, fr, bl, br;

    RobotMovement movement = new RobotMovement();
    FirstOdo odo = new FirstOdo(null, null, null);
    ElapsedTime eTime = new ElapsedTime();

    enum questionaire {question, left, right, leftNear, leftFar, rightNear, rightFar}
    enum parkAutoPath {leftNear, rightNear, leftFar, rightFar, running}
    questionaire question = questionaire.question;
    parkAutoPath autoPath = parkAutoPath.leftNear;

    @Override
    public void init() {
        fl = hardwareMap.dcMotor.get("fl");
        bl = hardwareMap.dcMotor.get("bl");
        fr = hardwareMap.dcMotor.get("fr");
        br = hardwareMap.dcMotor.get("br");

        odo.odoReset(fl,fr, bl);
        odo.setGlobalX(0);
        odo.setGlobalY(0);
    }

    @Override
    public void init_loop() {
        switch (question){
            case question:
                telemetry.addData("which side", "x for left, a for right");
                if (eTime.time() > 0.3){
                    if (gamepad1.a){question = question.right;}
                    if (gamepad1.x){question = question.left;}
                    eTime.reset();
                }
                break;
            case left:
                telemetry.addData("Left, far or near", "x for far, a for near");
                if (eTime.time() > 0.3){
                    if (gamepad1.a){question = question.leftNear;}
                    if (gamepad1.x){question = question.leftFar;}
                    eTime.reset();
                }
                break;
            case right:
                telemetry.addData("Right, far or near", "x for far, a for near");
                if (eTime.time() > 0.3){
                    if (gamepad1.a){question = question.rightNear;}
                    if (gamepad1.x){question = question.rightFar;}
                    eTime.reset();
                }
                break;
            case leftNear:
                telemetry.addData("left Near", "and i oop");
                autoPath = parkAutoPath.leftNear;
                break;
            case leftFar:
                telemetry.addData("left Far", "and i oop");
                autoPath = parkAutoPath.leftFar;
                break;
            case rightNear:
                telemetry.addData("right Near", "and i oop");
                autoPath = parkAutoPath.rightNear;
                break;
            case rightFar:
                telemetry.addData("right Far", "and i oop");
                autoPath = parkAutoPath.rightFar;
                break;
        }
    }

    @Override
    public void loop() {
        odo.odoloop();
        movement.movementUpdate(odo.globalX, odo.globalY, odo.getHeading(), fl, fr, bl, br);

        ArrayList<Waypoint> path = new ArrayList<>();

        switch (autoPath){
            case leftFar:
                path.add(new Waypoint(0,0,2, 1, 1));
                path.add(new Waypoint(2,6,2, 0.6, 0.4));
                path.add(new Waypoint(-4,6,1, 0.4, 0.3));
                autoPath = parkAutoPath.running;
                break;
            case leftNear:
                path.add(new Waypoint(0,0,2, 1, 1));
                path.add(new Waypoint(4,0,2, 0.6, 0.4, 0));
                autoPath = parkAutoPath.running;
                break;
            case rightFar:
                path.add(new Waypoint(0,0,2, 1, 1));
                path.add(new Waypoint(-2,6,2, 0.6, 0.4));
                path.add(new Waypoint(44,6,1, 0.4, 0.3));
                autoPath = parkAutoPath.running;
                break;
            case rightNear:
                path.add(new Waypoint(0,0,2, 1, 1));
                path.add(new Waypoint(-4,0,2, 0.6, 0.4, 0));
                autoPath = parkAutoPath.running;
            case running:
                telemetry.addData("running", "all gud prolly");
        }
        movement.followPath(path);
    }
}
