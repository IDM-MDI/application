package by.ishangulyev.application.controller.command.impl.add;

import by.ishangulyev.application.controller.AttributeName;
import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.RouterType;
import by.ishangulyev.application.controller.command.ActionCommand;
import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.dao.impl.DaoBattery;
import by.ishangulyev.application.model.entity.impl.Battery;
import by.ishangulyev.application.service.BatteryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class AddBatteryCommand implements ActionCommand {
    private BatteryService service = BatteryService.getInstance();
    @Override public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router;
        String name = request.getParameter(AttributeName.BATTERY_NAME);
        String mah = request.getParameter(AttributeName.BATTERY_MAH);
        if(service.add(name,mah)){
            router = service.get(request);
        }
        else{
            router = new Router(JspPath.ERROR400,RouterType.FORWARD);
        }
        return router;
    }
}
