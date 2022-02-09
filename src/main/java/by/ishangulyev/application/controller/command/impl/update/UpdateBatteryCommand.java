package by.ishangulyev.application.controller.command.impl.update;

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

public class UpdateBatteryCommand implements ActionCommand {
    private BatteryService service = BatteryService.getInstance();
    @Override public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router;
        String id = request.getParameter(AttributeName.BATTERY_ID);
        String name = request.getParameter(AttributeName.BATTERY_NAME);
        String mah = request.getParameter(AttributeName.BATTERY_MAH);
        if(service.update(id,name,mah)){
            router = service.get(request);
        }
        else{
            router = new Router(JspPath.ERROR400, RouterType.FORWARD);
        }
        return router;
    }
}
