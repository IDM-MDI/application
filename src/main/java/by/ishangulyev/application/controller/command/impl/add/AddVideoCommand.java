package by.ishangulyev.application.controller.command.impl.add;

import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.RouterType;
import by.ishangulyev.application.controller.command.ActionCommand;
import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.service.AudioService;
import by.ishangulyev.application.service.VideoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddVideoCommand implements ActionCommand {
    private VideoService service = VideoService.getInstance();
    @Override public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router;
        String name = request.getParameter("videoName");
        String resolution = request.getParameter("videoResolution");
        String ratio = request.getParameter("videoRatio");
        String brightness = request.getParameter("videoBrightness");
        String type = request.getParameter("videoType");

        if(service.add(name,resolution,ratio,brightness,type)){
            router = service.get(request);
        }
        else{
            router = new Router(JspPath.ERROR400, RouterType.FORWARD);
        }
        return router;
    }
}
