package by.ishangulyev.application.controller.command.impl.add;

import by.ishangulyev.application.controller.AttributeName;
import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.RouterType;
import by.ishangulyev.application.controller.command.ActionCommand;
import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.service.AudioService;
import by.ishangulyev.application.service.MemoryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddMemoryCommand implements ActionCommand {
    private MemoryService service = MemoryService.getInstance();
    @Override public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router;
        String name = request.getParameter(AttributeName.MEMORY_NAME);
        String type = request.getParameter(AttributeName.MEMORY_TYPE);
        String size = request.getParameter(AttributeName.MEMORY_SIZE);
        if(service.add(name,type,size)){
            router = service.get(request);
        }
        else{
            router = new Router(JspPath.ERROR400, RouterType.FORWARD);
        }
        return router;
    }
}
