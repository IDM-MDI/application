package by.ishangulyev.application.controller.command.impl;

import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.RouterType;
import by.ishangulyev.application.controller.command.ActionCommand;
import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.controller.command.LanguageType;
import by.ishangulyev.application.controller.command.RequestParameter;
import by.ishangulyev.application.service.LanguageService;
import by.ishangulyev.application.validator.CookieValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LanguageCommand implements ActionCommand {
    private LanguageService service = new LanguageService();
    private CookieValidator validator = new CookieValidator();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router = new Router(JspPath.INDEX, RouterType.FORWARD);
        if(!validator.isLanguageExist(request.getCookies())){
            switch (request.getParameter(RequestParameter.LANGUAGE.name().toLowerCase())){
                case "ru"->{
                    service.createCookie(LanguageType.RU);
                }
                case "en"->{
                    service.createCookie(LanguageType.EN);
                }
            }
        }
        return router;
    }
}
