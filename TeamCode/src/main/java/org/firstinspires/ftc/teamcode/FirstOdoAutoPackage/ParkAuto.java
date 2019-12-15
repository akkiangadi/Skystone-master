package org.firstinspires.ftc.teamcode.FirstOdoAutoPackage;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.ArrayList;

@Autonomous(name = "ParkAuto", group = "Workers")
public class ParkAuto extends OpMode {

    DcMotor fl, fr, bl, br;

    RobotMovement movement = new RobotMovement();
    FirstOdo odo = new FirstOdo(null, null, null);



    @Override
    public void init() {
        fl = hardwareMap.dcMotor.get("fl");
        bl = hardwareMap.dcMotor.get("bl");
        fr = hardwareMap.dcMotor.get("fr");
        br = hardwareMap.dcMotor.get("br");

        movement.setEverything(fl, fr, bl, br);
        odo.odoReset(fl,fr, bl);
        odo.setGlobalX(0);
        odo.setGlobalY(0);

    }

    @Override
    public void loop() {
        odo.odoloop();

        ArrayList<Waypoint> path = new ArrayList<>();
        path.add(new Waypoint(0,0,2, 1, 1));
        path.add(new Waypoint(100,150,2, 1, 1, Math.toRadians(90)));
        path.add(new Waypoint(30,70,2, 1, 1));

        movement.movementUpdate(odo.globalX, odo.globalY, odo.getHeading());
        movement.followPath(path);

    }

}
