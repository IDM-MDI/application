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

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        String language = request.getParameter("language");
        SessionService sessionService = SessionService.getInstance();
        CookieService cookieService = CookieService.getInstance();
        LanguageType type = LanguageType.valueOf(language.toUpperCase());
        cookieService.addLanguage(request,response,language);
        sessionService.addLanguage(request,type);
        return new Router(JspPath.INDEX, RouterType.FORWARD,type);
    }
}
