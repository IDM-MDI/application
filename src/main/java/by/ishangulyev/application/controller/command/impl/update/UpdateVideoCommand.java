package by.ishangulyev.application.controller.command.impl.update;

import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.RouterType;
import by.ishangulyev.application.controller.command.ActionCommand;
import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.service.BatteryService;
import by.ishangulyev.application.service.VideoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateVideoCommand implements ActionCommand {
    private VideoService service = VideoService.getInstance();
    @Override public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router;
        String name = request.getParameter("videoName");
        String id = request.getParameter("videoId");
        String resolution = request.getParameter("videoResolution");
        String ratio = request.getParameter("videoRatio");
        String brightness = request.getParameter("videoBrightness");
        String type = request.getParameter("videoType");

        if(service.update(id,name,resolution,ratio,brightness,type)){
            router = service.get(request);
        }
        else{
            router = new Router(JspPath.ERROR400, RouterType.FORWARD);
        }
        return router;
    }
}
