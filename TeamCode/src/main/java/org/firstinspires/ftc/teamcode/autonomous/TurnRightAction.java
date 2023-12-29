package org.firstinspires.ftc.teamcode.autonomous;

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
    public void printAction(boolean isActive) {
        this.telemetry.addLine((isActive ? "> " : "") + "Turn Right " + degrees + " Degrees");
    }
}
