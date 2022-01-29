package by.ishangulyev.application.controller.command.impl;

import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.command.ActionCommand;
import by.ishangulyev.application.service.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SignInCommand implements ActionCommand {
    private SessionService service = new SessionService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router = new Router();

        return router;
    }
}
