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
            router = adminUpdateAccount(request,response);
        }
        else{
            router = new Router(JspPath.ERROR4XX,RouterType.FORWARD);
        }
        return router;
    }

    private Router adminUpdateAccount(HttpServletRequest request, HttpServletResponse response) {
        User update = new User();
        update.setRole(Role.ADMIN);
        DaoUser daoUser = new DaoUser();
        daoUser.update(update);
        return new Router(JspPath.USERS,RouterType.FORWARD);
    }

    private Router userUpdateAccount(HttpServletRequest request, HttpServletResponse response){
        UserValidator validator = UserValidator.getInstance();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String username = request.getParameter("username");
        User update = new User();
        update.setEmail(email);
        try {
            Part part = request.getPart("userPhoto");
            if(part != null){
                if(validator.isPhotoValid(part)){
                    try(InputStream inputStream = part.getInputStream()){
                        update.setPhoto(inputStream.readAllBytes());
                    }
                }
            }
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
        update.setName(username);
        if(!password.isEmpty()) {
            try {
                update.setPass(HashPassGenerator.generate(password));
            } catch (NoSuchAlgorithmException e) {
                update.setPass(null);
            }
        }

        DaoUser daoUser = new DaoUser();
        daoUser.update(update);

        SessionService sessionService = new SessionService();
        sessionService.updateUser(request,update);

        CookieService cookieService = new CookieService();
        cookieService.addUser(request,response,update);

        return new Router(JspPath.ACCOUNT,RouterType.FORWARD);
    }
}
