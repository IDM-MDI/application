package by.ishangulyev.application.controller.command.impl;

import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.RouterType;
import by.ishangulyev.application.controller.command.ActionCommand;
import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SignUpCommand implements ActionCommand {
    private UserService service = UserService.getInstance();
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router;
        String email =  request.getParameter("email");
        String pass = request.getParameter("pass");
        if(service.registration(email,pass)){
            router = new Router(JspPath.SIGN_IN, RouterType.FORWARD);
        }
        else{
            router = new Router(JspPath.SIGN_UP,RouterType.FORWARD);
        }
        return router;
    }


}
