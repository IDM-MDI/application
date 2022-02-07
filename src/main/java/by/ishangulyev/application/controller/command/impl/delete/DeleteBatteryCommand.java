package by.ishangulyev.application.controller.command.impl.delete;

import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.RouterType;
import by.ishangulyev.application.controller.command.ActionCommand;
import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.dao.impl.DaoBattery;
import by.ishangulyev.application.model.entity.impl.Battery;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteBatteryCommand implements ActionCommand {
    @Override public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router;
        DaoBattery daoBattery = new DaoBattery();
        try {
            long delete = Long.parseLong(request.getParameter("batteryId"));
            daoBattery.delete(delete);
            router = new Router(JspPath.SETTINGS, RouterType.FORWARD);
        } catch (NumberFormatException e) {
            router = new Router(JspPath.ERROR4XX,RouterType.FORWARD);
        }
        return router;
    }

}
