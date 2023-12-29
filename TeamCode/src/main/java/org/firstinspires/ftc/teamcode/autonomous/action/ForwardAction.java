package org.firstinspires.ftc.teamcode.autonomous.action;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.RugratHardware;

public class ForwardAction extends AbstractAction {

    public int driveInches = 6;

    public ForwardAction(RugratHardware rugrat, Telemetry telemetry) {
        super(rugrat, telemetry, "Drive Straight ___ Inches");
    }

    @Override
    public void run() {

        super.rugrat.driveInches(driveInches);

    }

    @Override
    public void modifyLeft() {
        this.driveInches--;
        if(this.driveInches < 0)
            this.driveInches = 0;
    }

    @Override
    public void modifyRight() {
        this.driveInches++;
    }

    @Override
    public void printAction(boolean isActive, boolean isRunning) {
        this.telemetry.addLine((isActive || isRunning  ? "> " : "  ") + "Drive Straight " + driveInches + " Inches " + (isRunning ? "< " : ""));
    }
}
