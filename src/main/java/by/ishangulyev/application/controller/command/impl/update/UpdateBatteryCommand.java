package by.ishangulyev.application.controller.command.impl.update;

import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.RouterType;
import by.ishangulyev.application.controller.command.ActionCommand;
import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.dao.impl.DaoBattery;
import by.ishangulyev.application.model.entity.impl.Battery;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateBatteryCommand implements ActionCommand {

    @Override public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router;
        Battery battery = null;
        DaoBattery daoBattery = new DaoBattery();
        try {
            battery = fillEntityInfo(request);
            router = new Router(JspPath.SETTINGS, RouterType.FORWARD);
        } catch (Exception e) {
            router = new Router(JspPath.ERROR4XX,RouterType.FORWARD);
        }
        daoBattery.update(battery);
        return router;
    }

    private Battery fillEntityInfo(HttpServletRequest request) throws Exception {
        Battery result = new Battery();
        result.setName(request.getParameter("batteryName"));
        try{
            long id = Integer.parseInt(request.getParameter("batteryId"));
            int mah = Integer.parseInt(request.getParameter("batteryMah"));
            result.setId(id);
            result.setMah(mah);
        }catch (NumberFormatException e){
            throw new Exception();
        }
        return result;
    }
}
