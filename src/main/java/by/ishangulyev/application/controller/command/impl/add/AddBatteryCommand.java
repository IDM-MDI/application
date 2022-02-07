package by.ishangulyev.application.controller.command.impl.add;

import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.RouterType;
import by.ishangulyev.application.controller.command.ActionCommand;
import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.dao.impl.DaoBattery;
import by.ishangulyev.application.dao.impl.DaoCart;
import by.ishangulyev.application.dao.impl.DaoUser;
import by.ishangulyev.application.model.entity.impl.Battery;
import by.ishangulyev.application.model.entity.impl.Cart;
import by.ishangulyev.application.model.entity.impl.User;
import by.ishangulyev.application.util.HashPassGenerator;
import by.ishangulyev.application.validator.UserValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.security.NoSuchAlgorithmException;

public class AddBatteryCommand implements ActionCommand {
    @Override public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router;
        DaoBattery daoBattery = new DaoBattery();
        try {
            Battery battery = fillEntityInfo(request);
            daoBattery.create(battery);
            router = new Router(JspPath.SETTINGS,RouterType.FORWARD);
        } catch (Exception e) {
            router = new Router(JspPath.ERROR4XX,RouterType.FORWARD);
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
