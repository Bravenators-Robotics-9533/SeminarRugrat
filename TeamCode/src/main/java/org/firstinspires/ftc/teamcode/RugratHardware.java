package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.Range;

import org.checkerframework.checker.index.qual.LTEqLengthOf;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public class RugratHardware {

    public static final int TICKS_PER_MOTOR_REV = 288;
    public static final double WHEEL_CIRCUMFERENCE = 3.54 * Math.PI; // 3.54 diam.

    public static final double TICKS_PER_INCH = TICKS_PER_MOTOR_REV / WHEEL_CIRCUMFERENCE;

    public static final double MOTOR_POWER = 1.0;
    public static final double MAX_TURN_POWER = 0.5;

    public static final double PK = 0.005;

    public DcMotorEx left;
    public DcMotorEx right;

    private IMU imu;

    private final LinearOpMode opMode;

    public RugratHardware(LinearOpMode opMode, HardwareMap hardwareMap) {

        this.opMode = opMode;

        this.left  = hardwareMap.get(DcMotorEx.class, "l");
        this.right = hardwareMap.get(DcMotorEx.class, "r");

        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD
        );

        this.imu = hardwareMap.get(IMU.class, "imu");
        this.imu.initialize(new IMU.Parameters(orientationOnRobot));
        this.imu.resetYaw();

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

    private boolean isBusy() {
        return this.left.isBusy() || this.right.isBusy();
    }

    public void driveInches(double leftInches, double rightInches) {

        leftInches  = -leftInches;
        rightInches = -rightInches;

        this.left.setTargetPosition(this.left.getCurrentPosition() + (int) (leftInches * TICKS_PER_INCH));
        this.right.setTargetPosition(this.right.getCurrentPosition() + (int) (rightInches * TICKS_PER_INCH));

        this.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        this.left.setPower(MOTOR_POWER);
        this.right.setPower(MOTOR_POWER);

        while(opMode.opModeIsActive()) {
            if(!this.isBusy())
                break;
        }

        this.left.setPower(0);
        this.right.setPower(0);

        this.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void driveInches(double inches) {
        this.driveInches(inches, inches);
    }

    private double getHeading() {
        YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
        return orientation.getYaw(AngleUnit.DEGREES);
    }

    private double getSteeringCorrection(double desiredHeading) {

        // Determine the heading current error
        double headingError = desiredHeading - getHeading();

        // Normalize the error to be within +/- 180 degrees
        while (headingError > 180)  headingError -= 360;
        while (headingError <= -180) headingError += 360;

        // Multiply the error by the gain to determine the required steering correction/  Limit the result to +/- 1.0
        return Range.clip(headingError * PK, -MAX_TURN_POWER, MAX_TURN_POWER);
    }

    public void turnDegrees(double degrees) {

        this.imu.resetYaw();
        double targetHeading = getHeading() + degrees;

        double correction = getSteeringCorrection(targetHeading);

        while(opMode.opModeIsActive() && Math.abs(correction) > 0.002) {

            correction = getSteeringCorrection(targetHeading);

            this.left.setPower(correction);
            this.right.setPower(-correction);

        }

        this.left.setPower(0);
        this.right.setPower(0);

    }

}
