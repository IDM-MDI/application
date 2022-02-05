package by.ishangulyev.application.controller.command.impl;

import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.RouterType;
import by.ishangulyev.application.controller.command.ActionCommand;
import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.dao.impl.DaoUser;
import by.ishangulyev.application.exception.DataBaseException;
import by.ishangulyev.application.model.entity.impl.User;
import by.ishangulyev.application.service.CookieService;
import by.ishangulyev.application.service.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Optional;

public class SignOutCommand implements ActionCommand {
    private SessionService sessionServic = new SessionService();
    private CookieService cookieService = new CookieService();

    @Override public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router = new Router(JspPath.SIGN_IN, RouterType.FORWARD);
        sessionServic.removeUser(request);
        cookieService.removeUser(request,response);
        return router;
    }
}
