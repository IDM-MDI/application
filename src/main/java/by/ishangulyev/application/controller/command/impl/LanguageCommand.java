package by.ishangulyev.application.controller.command.impl;

import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.RouterType;
import by.ishangulyev.application.controller.command.ActionCommand;
import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.controller.command.LanguageType;
import by.ishangulyev.application.service.CookieService;
import by.ishangulyev.application.service.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LanguageCommand implements ActionCommand {
    private SessionService sessionService = new SessionService();
    private CookieService cookieService = new CookieService();
    private LanguageType type;

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        this.type = LanguageType.valueOf(request.getParameter("language").toUpperCase());
        cookieService.addLanguage(request,response,request.getParameter("language"));
        sessionService.addLanguage(request,type);
        return new Router(JspPath.INDEX, RouterType.FORWARD,type);
    }
}
