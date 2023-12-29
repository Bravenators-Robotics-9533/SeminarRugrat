package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RugratHardware;
import org.firstinspires.ftc.teamcode.gamepad.FtcGamePad;

import java.util.ArrayList;

@TeleOp(name="Controller Autonomous", group="Above")
public class ControllerAutonomous extends LinearOpMode {

    private RugratHardware rugrat;
    private ArrayList<AbstractAction> actions = new ArrayList<>();

    private ArrayList<Integer> activeActions = new ArrayList<>();

    private boolean isRunning = false;

    @Override
    public void runOpMode() throws InterruptedException {

        super.telemetry.setAutoClear(true);

        this.rugrat = new RugratHardware(this, this.hardwareMap);
        FtcGamePad gamepad = new FtcGamePad("Gamepad 1", gamepad1, this::gamepadButtonEvent);

        waitForStart();

        while(opModeIsActive()) {

            if(isRunning) {

                this.activeActions.clear();

                for(int i = 0; i < actions.size(); i++) {

                    this.activeActions.clear();
                    this.activeActions.add(i);
                    printActions();

                    this.actions.get(i).run();

                }

                this.activeActions.clear();
                this.isRunning = false;
            }

            gamepad.update();
            this.printActions();
        }

    }

    private void printActions() {
        telemetry.clear();

        for(int i = 0; i < actions.size(); i++) {
            actions.get(i).printAction(this.activeActions.contains(i));
        }

        if(actions.size() == 0)
            telemetry.addLine();

        telemetry.update();
    }

    private void handleSelection() {

    }

    private void gamepadButtonEvent(FtcGamePad gamepad, int button, boolean isPressed) {

        switch (button) {

            case FtcGamePad.GAMEPAD_DPAD_UP:
                if(isPressed)
                    actions.add(new ForwardAction(this.rugrat, super.telemetry));

                break;

            case FtcGamePad.GAMEPAD_DPAD_DOWN:
                if(isPressed)
                    actions.add(new BackwardAction(this.rugrat, super.telemetry));

                break;

            case FtcGamePad.GAMEPAD_DPAD_LEFT:
                if(isPressed) {
                    actions.add(new TurnLeftAction(this.rugrat, super.telemetry));
                }

                break;

            case FtcGamePad.GAMEPAD_DPAD_RIGHT:
                if(isPressed) {
                    actions.add(new TurnRightAction(this.rugrat, super.telemetry));
                }

                break;

            case FtcGamePad.GAMEPAD_A:
                if(isPressed) {
                    isRunning = true;
                }

                break;

            case FtcGamePad.GAMEPAD_X:
                if(isPressed) {
                    this.actions.clear();
                }

                break;

            case FtcGamePad.GAMEPAD_B:
                if(isPressed && this.actions.size() > 0) {
                    this.actions.remove(this.actions.size() - 1);
                }

                break;

        }

    }

}
