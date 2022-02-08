package by.ishangulyev.application.controller.command.impl;

import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.RouterType;
import by.ishangulyev.application.controller.command.ActionCommand;
import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.service.CookieService;
import by.ishangulyev.application.service.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Optional;

public class SignOutCommand implements ActionCommand {
    @Override public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router = new Router(JspPath.SIGN_IN, RouterType.FORWARD);
        SessionService sessionServic = SessionService.getInstance();
        CookieService cookieService = CookieService.getInstance();
        sessionServic.removeUser(request.getSession());
        cookieService.removeUser(request,response);
        return router;
    }
}
