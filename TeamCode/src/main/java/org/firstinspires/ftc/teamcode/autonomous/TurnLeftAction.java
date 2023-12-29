package org.firstinspires.ftc.teamcode.autonomous;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.RugratHardware;

public class TurnLeftAction extends AbstractAction {

    public int degrees = 45;

    public TurnLeftAction(RugratHardware rugrat, Telemetry telemetry) {
        super(rugrat, telemetry, "Turn Left ___ Degrees ");
    }

    @Override
    public void run() {

        super.rugrat.turnDegrees(degrees);

    }

    @Override
    public void printAction(boolean isActive) {
        this.telemetry.addLine((isActive ? "> " : "") + "Turn Left " + degrees + " Degrees");
    }
}
