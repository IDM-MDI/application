package by.ishangulyev.application.controller.command.impl.get;

import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.command.ActionCommand;
import by.ishangulyev.application.service.AudioService;
import by.ishangulyev.application.service.BatteryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetAudioCommand implements ActionCommand {
    private AudioService service = AudioService.getInstance();
    @Override public Router execute(HttpServletRequest request, HttpServletResponse response) {
        return service.get(request);
    }
}
