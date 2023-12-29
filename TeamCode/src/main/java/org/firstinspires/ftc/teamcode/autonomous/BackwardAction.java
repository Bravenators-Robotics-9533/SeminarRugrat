package org.firstinspires.ftc.teamcode.autonomous;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.RugratHardware;

public class BackwardAction extends AbstractAction {

    public int driveInches = 4;

    public BackwardAction(RugratHardware rugrat, Telemetry telemetry) {
        super(rugrat, telemetry, "Drive Backwards ___ Inches");
    }

    @Override
    public void run() {

        super.rugrat.driveInches(-driveInches);

    }

    @Override
    public void printAction(boolean isActive) {
        this.telemetry.addLine((isActive ? "> " : "") + "Drive Backwards " + driveInches + " Inches");
    }
}
