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
import by.ishangulyev.application.service.UserService;
import by.ishangulyev.application.util.HashPassGenerator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public class SignInCommand implements ActionCommand {
    private UserService service = UserService.getInstance();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router;
        SessionService sessionService = SessionService.getInstance();
        CookieService cookieService = CookieService.getInstance();
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        User user = service.login(email,pass);

        if(user == null){
            router = new Router(JspPath.SIGN_IN,RouterType.FORWARD);
        }
        else{
            router = new Router(JspPath.INDEX, RouterType.FORWARD);
            sessionService.addUser(request,user);
            cookieService.addUser(request,response,user);
        }
        return router;
    }
}
