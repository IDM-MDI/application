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
        User updateUser = fillEntityInfo(request);
        User sessionUser= (User) session.getAttribute("user");
        service.updateAccount(updateUser);
        if(updateUser.getEmail().equals(sessionUser.getEmail())){
            SessionService sessionService = SessionService.getInstance();
            sessionService.updateUser(request.getSession(),updateUser);

            CookieService cookieService = CookieService.getInstance();
            cookieService.addUser(request,response,updateUser);
            router = new Router(JspPath.ACCOUNT,RouterType.FORWARD);
        }
        else if(updateUser.getRole() != null){
            router = new Router(JspPath.USER_SETTINGS,RouterType.FORWARD);
            String page = "1";
            int pageNumber = Integer.parseInt(page);
            int next = 0,prev = pageNumber-1;

            List<User> userList = service.getAccounts(page);
            if(userList.size() > 9){
                userList.remove(userList.size() -1);
                next = pageNumber+1;
            }

            request.setAttribute("userList",userList);
            request.setAttribute("currentPage",pageNumber);
            request.setAttribute("nextPage",next);
            request.setAttribute("prevPage",prev);
        }
        else{
            router = new Router(JspPath.ERROR400,RouterType.FORWARD);
        }
        return router;
    }

    private User fillEntityInfo(HttpServletRequest request){
        UserValidator validator = UserValidator.getInstance();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String username = request.getParameter("username");
        String role = request.getParameter("role");

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
        if(role != null) {
            result.setRole(Role.valueOf(role.toUpperCase()));
        }
        if(!password.isEmpty()) {
            result.setPass(HashPassGenerator.generate(password));
        }

        return result;
    }
}
