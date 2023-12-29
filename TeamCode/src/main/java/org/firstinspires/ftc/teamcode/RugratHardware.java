package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class RugratHardware {

    public DcMotorEx left;
    public DcMotorEx right;

    public RugratHardware(HardwareMap hardwareMap) {

        this.left  = hardwareMap.get(DcMotorEx.class, "l");
        this.right = hardwareMap.get(DcMotorEx.class, "r");

        this.left.setDirection(DcMotorSimple.Direction.FORWARD);
        this.right.setDirection(DcMotorSimple.Direction.REVERSE);

        this.left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        this.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void setMode(DcMotor.RunMode runMode) {
        this.left.setMode(runMode);
        this.right.setMode(runMode);
    }

}
