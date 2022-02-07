package by.ishangulyev.application.controller.command.impl.update;

import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.RouterType;
import by.ishangulyev.application.controller.command.ActionCommand;
import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.dao.impl.DaoUser;
import by.ishangulyev.application.model.entity.impl.Role;
import by.ishangulyev.application.model.entity.impl.User;
import by.ishangulyev.application.service.CookieService;
import by.ishangulyev.application.service.SessionService;
import by.ishangulyev.application.util.HashPassGenerator;
import by.ishangulyev.application.validator.UserValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;

public class UpdateUserCommand implements ActionCommand {
    @Override public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router;
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        User sessionUser= (User) session.getAttribute("user");

        if(email.equals(sessionUser.getEmail())) {
            router = userUpdateAccount(request,response);
        }
        else if(request.getParameter("role") != null){
            router = adminUpdateAccount(request);
        }
        else{
            router = new Router(JspPath.ERROR4XX,RouterType.FORWARD);
        }
        return router;
    }

    private Router adminUpdateAccount(HttpServletRequest request) {
        User update = fillEntityInfo(request);
        String role = request.getParameter("role").toUpperCase();

        if(role != null){
            update.setRole(Role.valueOf(role));
        }
        DaoUser daoUser = new DaoUser();
        daoUser.update(update);

        return new Router(JspPath.USERSETTINGS,RouterType.FORWARD);
    }

    private Router userUpdateAccount(HttpServletRequest request, HttpServletResponse response){
        User update = fillEntityInfo(request);

        DaoUser daoUser = new DaoUser();
        daoUser.update(update);

        SessionService sessionService = new SessionService();
        sessionService.updateUser(request,update);

        CookieService cookieService = new CookieService();
        cookieService.addUser(request,response,update);

        return new Router(JspPath.ACCOUNT,RouterType.FORWARD);
    }

    private User fillEntityInfo(HttpServletRequest request){
        UserValidator validator = UserValidator.getInstance();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String username = request.getParameter("username");
        User result = new User();
        result.setEmail(email);
        try {
            Part part = request.getPart("userPhoto");
            if(part != null){
                if(validator.isPhotoValid(part)){
                    try(InputStream inputStream = part.getInputStream()){
                        result.setPhoto(inputStream.readAllBytes());
                    }
                }
            }
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
        result.setName(username);
        if(!password.isEmpty()) {
            try {
                result.setPass(HashPassGenerator.generate(password));
            } catch (NoSuchAlgorithmException e) {
                result.setPass(null);
            }
        }
        return result;
    }
}
