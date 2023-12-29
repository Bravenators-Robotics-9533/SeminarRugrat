package org.firstinspires.ftc.teamcode.autonomous.action;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.RugratHardware;

public abstract class AbstractAction {

    public final String actionName;

    protected final RugratHardware rugrat;

    protected final Telemetry telemetry;

    public AbstractAction(RugratHardware rugrat, Telemetry telemetry, String actionName) {
        this.rugrat = rugrat;
        this.telemetry = telemetry;
        this.actionName = actionName;
    }

    public abstract void run();

    public abstract void modifyLeft();
    public abstract void modifyRight();

    public void printAction(boolean isActive, boolean isRunning) {
        this.telemetry.addLine((isActive || isRunning  ? "> " : "") + this.actionName + (isRunning ? " < " : ""));
    }

}
