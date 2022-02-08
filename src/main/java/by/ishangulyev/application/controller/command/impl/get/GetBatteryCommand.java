package by.ishangulyev.application.controller.command.impl.get;

import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.RouterType;
import by.ishangulyev.application.controller.command.ActionCommand;
import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.dao.impl.DaoBattery;
import by.ishangulyev.application.exception.DaoException;
import by.ishangulyev.application.model.entity.impl.Battery;
import by.ishangulyev.application.service.BatteryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class GetBatteryCommand implements ActionCommand {
    private BatteryService service = BatteryService.getInstance();
    @Override public Router execute(HttpServletRequest request, HttpServletResponse response) {
        return service.get(request);
    }
}
