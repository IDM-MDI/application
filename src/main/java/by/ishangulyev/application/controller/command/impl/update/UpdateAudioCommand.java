package by.ishangulyev.application.controller.command.impl.update;

import by.ishangulyev.application.controller.AttributeName;
import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.RouterType;
import by.ishangulyev.application.controller.command.ActionCommand;
import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.service.AudioService;
import by.ishangulyev.application.service.BatteryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateAudioCommand implements ActionCommand {
    private AudioService service = AudioService.getInstance();
    @Override public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router;
        String id = request.getParameter(AttributeName.AUDIO_ID);
        String name = request.getParameter(AttributeName.AUDIO_NAME);
        String type = request.getParameter(AttributeName.AUDIO_TYPE);
        String frequency = request.getParameter(AttributeName.AUDIO_FREQUENCY);
        if(service.update(id,name,type,frequency)){
            router = service.get(request);
        }
        else{
            router = new Router(JspPath.ERROR400, RouterType.FORWARD);
        }
        return router;
    }
}
