package org.firstinspires.ftc.teamcode.FirstOdoAutoPackage;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import java.util.ArrayList;

@Autonomous(name = "ParkAuto", group = "Workers")
public class ParkAuto extends OpMode {

    DcMotor fl, fr, bl, br;

    RobotMovement movement = new RobotMovement();



    @Override
    public void init() {
        fl = hardwareMap.dcMotor.get("fl");
        bl = hardwareMap.dcMotor.get("bl");
        fr = hardwareMap.dcMotor.get("fr");
        br = hardwareMap.dcMotor.get("br");

        movement.setEverything(fl, fr, bl, br, 0, 0);

    }

    @Override
    public void loop() {
        movement.getOdo().odoloop();

        ArrayList<Waypoint> path = new ArrayList<>();
        path.add(new Waypoint(0,0,2, 1, 1));
        path.add(new Waypoint(100,150,2, 1, 1));
        path.add(new Waypoint(30,70,2, 1, 1));

        movement.followPath(path);

    }

}
