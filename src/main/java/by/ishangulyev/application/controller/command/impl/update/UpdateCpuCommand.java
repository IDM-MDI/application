package by.ishangulyev.application.controller.command.impl.update;

import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.RouterType;
import by.ishangulyev.application.controller.command.ActionCommand;
import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.service.BatteryService;
import by.ishangulyev.application.service.CpuService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateCpuCommand implements ActionCommand {
    private CpuService service = CpuService.getInstance();
    @Override public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router;
        String name = request.getParameter("cpuName");
        String id = request.getParameter("cpuId");
        String core = request.getParameter("cpuCore");
        String frequency = request.getParameter("cpuFrequency");
        String bit = request.getParameter("cpuBit");
        if(service.update(id,name,core,frequency,bit)){
            router = service.get(request);
        }
        else{
            router = new Router(JspPath.ERROR400, RouterType.FORWARD);
        }
        return router;
    }
}
