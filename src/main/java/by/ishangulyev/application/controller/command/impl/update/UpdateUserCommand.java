package by.ishangulyev.application.controller.command.impl.update;

import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.RouterType;
import by.ishangulyev.application.controller.command.ActionCommand;
import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.model.entity.impl.Role;
import by.ishangulyev.application.model.entity.impl.User;
import by.ishangulyev.application.service.CookieService;
import by.ishangulyev.application.service.SessionService;
import by.ishangulyev.application.service.UserService;
import by.ishangulyev.application.util.HashPassGenerator;
import by.ishangulyev.application.validator.UserValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UpdateUserCommand implements ActionCommand {
    private UserService service = UserService.getInstance();
    @Override public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router;
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String username = request.getParameter("username");
        String role = request.getParameter("role");
        Part part;
        try {
            part = request.getPart("userPhoto");
        } catch (IOException | ServletException e) {
            part = null;
        }
        User sessionUser= (User) session.getAttribute("user");
        User user = service.updateAccount(email,password,username,role,part);
        if(user.getEmail().equals(sessionUser.getEmail())){
            SessionService sessionService = SessionService.getInstance();
            sessionService.updateUser(request.getSession(),user);

            CookieService cookieService = CookieService.getInstance();
            cookieService.addUser(request,response,user);
            router = new Router(JspPath.ACCOUNT,RouterType.FORWARD);
        }
        else if(user.getRole() != null){
            router = service.getAccounts(request);
        }
        else{
            router = new Router(JspPath.ERROR400,RouterType.FORWARD);
        }
        return router;
    }
}
