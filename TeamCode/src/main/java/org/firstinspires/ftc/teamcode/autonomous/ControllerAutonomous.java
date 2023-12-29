package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RugratHardware;
import org.firstinspires.ftc.teamcode.autonomous.action.AbstractAction;
import org.firstinspires.ftc.teamcode.autonomous.action.BackwardAction;
import org.firstinspires.ftc.teamcode.autonomous.action.ForwardAction;
import org.firstinspires.ftc.teamcode.autonomous.action.TurnLeftAction;
import org.firstinspires.ftc.teamcode.autonomous.action.TurnRightAction;
import org.firstinspires.ftc.teamcode.gamepad.FtcGamePad;

import java.util.ArrayList;

@TeleOp(name="Controller Autonomous", group="Above")
public class ControllerAutonomous extends LinearOpMode {

    private RugratHardware rugrat;
    private ArrayList<AbstractAction> actions = new ArrayList<>();

    private int activeAction = -1;
    private int runningAction = 0;

    private boolean isRunning = false;

    @Override
    public void runOpMode() throws InterruptedException {

        super.telemetry.setAutoClear(true);

        this.rugrat = new RugratHardware(this, this.hardwareMap);
        FtcGamePad gamepad = new FtcGamePad("Gamepad 1", gamepad1, this::gamepadButtonEvent);

        waitForStart();

        while(opModeIsActive()) {

            if(isRunning) {

                for(int i = 0; i < actions.size(); i++) {

                    this.runningAction = i;

                    printActions();

                    this.actions.get(i).run();

                }

                this.isRunning = false;
            }

            handleSelection();
            gamepad.update();
            this.printActions();
        }

    }

    private void printActions() {
        telemetry.clear();

        for(int i = 0; i < actions.size(); i++) {
            actions.get(i).printAction(activeAction == i && !isRunning, isRunning && this.runningAction == i);
        }

        if(actions.size() == 0)
            telemetry.addLine();

        telemetry.update();
    }

    private double prevLeftY = 0;

    private void handleSelection() {

        if(gamepad1.left_stick_y < 0 && this.prevLeftY >= 0) {
            if(this.activeAction > 0) {
                this.activeAction--;
            }
        } else if(gamepad1.left_stick_y > 0 && this.prevLeftY <= 0) {
            if(this.activeAction < this.actions.size() - 1) {
                this.activeAction++;
            }
        }

        this.prevLeftY = gamepad1.left_stick_y;

    }

    private void addAction(AbstractAction action) {
        if(this.activeAction < 0)
            this.activeAction = 0;

        this.activeAction++;

        if(this.activeAction > this.actions.size() - 1) {
            actions.add(action);
            this.activeAction = this.actions.size() - 1;
        } else {
            actions.add(this.activeAction, action);
        }
    }

    private void gamepadButtonEvent(FtcGamePad gamepad, int button, boolean isPressed) {

        switch (button) {

            case FtcGamePad.GAMEPAD_DPAD_UP:
                if(isPressed) {
                    addAction(new ForwardAction(this.rugrat, super.telemetry));
                }

                break;

            case FtcGamePad.GAMEPAD_DPAD_DOWN:
                if(isPressed) {
                    addAction(new BackwardAction(this.rugrat, super.telemetry));
                }

                break;

            case FtcGamePad.GAMEPAD_DPAD_LEFT:
                if(isPressed) {
                    addAction(new TurnLeftAction(this.rugrat, super.telemetry));
                }

                break;

            case FtcGamePad.GAMEPAD_DPAD_RIGHT:
                if(isPressed) {
                    addAction(new TurnRightAction(this.rugrat, super.telemetry));
                }

                break;

            case FtcGamePad.GAMEPAD_LBUMPER:

                if(isPressed) {
                    if(this.activeAction >= 0 && this.activeAction < this.actions.size()) {
                        this.actions.get(this.activeAction).modifyLeft();
                    }
                }

                break;

            case FtcGamePad.GAMEPAD_RBUMPER:

                if(isPressed) {
                    if(this.activeAction >= 0 && this.activeAction < this.actions.size()) {
                        this.actions.get(this.activeAction).modifyRight();
                    }
                }

                break;

            case FtcGamePad.GAMEPAD_A:
                if(isPressed) {
                    isRunning = true;
                }

                break;

            case FtcGamePad.GAMEPAD_BACK:
                if(isPressed) {
                    this.activeAction = -1;
                    this.actions.clear();
                }

                break;

            case FtcGamePad.GAMEPAD_B:
                if(isPressed) {

                    if(this.activeAction >= 0 && this.activeAction < this.actions.size()) {
                        this.actions.remove(this.activeAction);

                        if(this.activeAction > this.actions.size() - 1)
                            this.activeAction = this.actions.size() - 1;

                        if(this.actions.size() == 0)
                            this.activeAction = -1;
                    }

                }

                break;

            case FtcGamePad.GAMEPAD_Y:

                break;

        }

    }

}
