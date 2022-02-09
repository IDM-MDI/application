package by.ishangulyev.application.controller.command.impl.delete;

import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.RouterType;
import by.ishangulyev.application.controller.command.ActionCommand;
import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.service.AudioService;
import by.ishangulyev.application.service.CpuService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteCpuCommand implements ActionCommand {
    private CpuService service = CpuService.getInstance();
    @Override public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router;
        String id = request.getParameter("cpuId");
        if(service.delete(id)){
            router = service.get(request);
        }
        else {
            router = new Router(JspPath.ERROR400, RouterType.FORWARD);
        }
        return router;
    }
}
