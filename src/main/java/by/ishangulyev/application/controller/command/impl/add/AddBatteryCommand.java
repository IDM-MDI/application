package by.ishangulyev.application.controller.command.impl.add;

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

public class AddBatteryCommand implements ActionCommand {
    private BatteryService service = BatteryService.getInstance();
    @Override public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router;
        try {
            Battery battery = fillEntityInfo(request);
            service.add(battery);
            router = service.get(request);;
        } catch (Exception e) {
            router = new Router(JspPath.ERROR400,RouterType.FORWARD);
        }
        return router;
    }

    private Battery fillEntityInfo(HttpServletRequest request) throws Exception {
        Battery result = new Battery();
        result.setName(request.getParameter("batteryName"));

        try{
            int mah = Integer.parseInt(request.getParameter("batteryMah"));
            result.setMah(mah);
        }catch (NumberFormatException e){
            throw new Exception();
        }
        return result;
    }
}
