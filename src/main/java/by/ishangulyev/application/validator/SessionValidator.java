package by.ishangulyev.application.validator;

import by.ishangulyev.application.controller.command.RequestParameterName;
import by.ishangulyev.application.model.entity.impl.Role;
import by.ishangulyev.application.model.entity.impl.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Locale;

public class SessionValidator {
    private static SessionValidator instance = new SessionValidator();

    private SessionValidator(){}

    public static SessionValidator getInstance(){
        return instance;
    }

    public boolean isAdmin(HttpSession session){
        User user = (User)session.getAttribute("user");
        return user.getRole() == Role.ADMIN;
    }


    public boolean isUser(HttpSession session){
        return session.getAttribute("user") != null;
    }

    public boolean isAdminPage(HttpServletRequest request){
        request.getParameter(RequestParameterName.JSP_SWITCHER.name().toLowerCase(Locale.ROOT));
        return true;
    }

    public boolean isUserPage(HttpServletRequest request) {
        return true;
    }
}
