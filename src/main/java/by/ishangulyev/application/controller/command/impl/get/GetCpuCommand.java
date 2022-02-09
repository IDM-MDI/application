package by.ishangulyev.application.controller.command.impl.get;

import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.command.ActionCommand;
import by.ishangulyev.application.service.BatteryService;
import by.ishangulyev.application.service.CpuService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetCpuCommand implements ActionCommand {
    private CpuService service = CpuService.getInstance();
    @Override public Router execute(HttpServletRequest request, HttpServletResponse response) {
        return service.get(request);
    }
}
