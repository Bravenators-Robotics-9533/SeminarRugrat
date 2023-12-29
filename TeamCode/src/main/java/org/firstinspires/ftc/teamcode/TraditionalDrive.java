package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Traditional Drive", group="Below")
public class TraditionalDrive extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        RugratHardware rugrat = new RugratHardware(this, this.hardwareMap);

        waitForStart();

        while(opModeIsActive()) {

            double drive    = gamepad1.left_stick_y;
            double turn     = gamepad1.right_stick_x;

            double left     = drive - turn;
            double right    = drive + turn;

            double max = Math.max(Math.abs(left), Math.abs(right));

            if(max > 1.0) {
                left    /= max;
                right   /= max;
            }

            rugrat.left.setPower(left);
            rugrat.right.setPower(right);

        }

    }

}
