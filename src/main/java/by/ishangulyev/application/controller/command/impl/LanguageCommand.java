package by.ishangulyev.application.controller.command.impl;

import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.RouterType;
import by.ishangulyev.application.controller.command.ActionCommand;
import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.controller.command.LanguageType;
import by.ishangulyev.application.service.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LanguageCommand implements ActionCommand {
    private SessionService service = new SessionService();
    private LanguageType type;

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        this.type = LanguageType.valueOf(request.getParameter("language").toUpperCase());
        return new Router(JspPath.INDEX, RouterType.FORWARD,type);
    }
}
