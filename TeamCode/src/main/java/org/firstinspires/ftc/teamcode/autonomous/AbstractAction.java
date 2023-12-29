package org.firstinspires.ftc.teamcode.autonomous;

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

        this.printAction(false);
    }

    public abstract void run();

    public void printAction(boolean isActive) {
        this.telemetry.addLine((isActive ? "> " : "") + this.actionName);
    }

}
