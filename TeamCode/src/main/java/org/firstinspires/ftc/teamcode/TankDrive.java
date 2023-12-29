package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name="Tank Drive")
public class TankDrive extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        DcMotorEx left  = hardwareMap.get(DcMotorEx.class, "l");
        DcMotorEx right = hardwareMap.get(DcMotorEx.class, "r");

        waitForStart();

        while(opModeInInit()) {

            double leftPower    = gamepad1.left_stick_y;
            double rightPower   = gamepad1.right_stick_y;

            left.setPower(leftPower);
            right.setPower(rightPower);

        }

    }

}
