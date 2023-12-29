package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Tank Drive")
public class TankDrive extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        RugratHardware rugrat = new RugratHardware(this.hardwareMap);

        waitForStart();

        while(opModeIsActive()) {

            double leftPower    = Math.pow(gamepad1.left_stick_y, 3);
            double rightPower   = Math.pow(gamepad1.right_stick_y, 3);

            leftPower   = Range.clip(leftPower, -1.0, 1.0);
            rightPower  = Range.clip(rightPower, -1.0, 1.0);

            rugrat.left.setPower(leftPower);
            rugrat.right.setPower(rightPower);

        }

    }

}
