package by.ishangulyev.application.controller.command.impl.add;

import by.ishangulyev.application.controller.AttributeName;
import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.RouterType;
import by.ishangulyev.application.controller.command.ActionCommand;
import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.service.AudioService;
import by.ishangulyev.application.service.CpuService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddCpuCommand implements ActionCommand {
    private CpuService service = CpuService.getInstance();
    @Override public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router;
        String name = request.getParameter(AttributeName.CPU_NAME);
        String core = request.getParameter(AttributeName.CPU_CORE);
        String frequency = request.getParameter(AttributeName.CPU_FREQUENCY);
        String bit = request.getParameter(AttributeName.CPU_BIT);
        if(service.add(name,core,frequency,bit)){
            router = service.get(request);
        }
        else{
            router = new Router(JspPath.ERROR400, RouterType.FORWARD);
        }
        return router;
    }
}
