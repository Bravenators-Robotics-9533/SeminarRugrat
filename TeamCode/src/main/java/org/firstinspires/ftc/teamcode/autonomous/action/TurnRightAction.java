package org.firstinspires.ftc.teamcode.autonomous.action;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.RugratHardware;

public class TurnRightAction extends AbstractAction {

    public int degrees = 45;

    public TurnRightAction(RugratHardware rugrat, Telemetry telemetry) {
        super(rugrat, telemetry, "Turn Right ___ Degrees ");
    }

    @Override
    public void run() {

        super.rugrat.turnDegrees(-degrees);

    }

    @Override
    public void modifyLeft() {

        degrees -= 45;

        if(degrees < 45)
            degrees = 45;

    }

    @Override
    public void modifyRight() {

        degrees += 45;

        if(degrees > 180)
            degrees = 180;

    }

    @Override
    public void printAction(boolean isActive, boolean isRunning) {
        this.telemetry.addLine((isActive || isRunning  ? "> " : "  ") + "Turn Right " + degrees + " Degrees " + (isRunning ? "< " : ""));
    }
}
