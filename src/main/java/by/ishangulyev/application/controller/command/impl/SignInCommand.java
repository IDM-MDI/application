package by.ishangulyev.application.controller.command.impl;

import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.RouterType;
import by.ishangulyev.application.controller.command.ActionCommand;
import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.dao.impl.DaoUser;
import by.ishangulyev.application.exception.DataBaseException;
import by.ishangulyev.application.model.entity.impl.User;
import by.ishangulyev.application.service.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Optional;

public class SignInCommand implements ActionCommand {
    private SessionService sessionServic = new SessionService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        DaoUser dao = new DaoUser();
        Router router = new Router(JspPath.INDEX, RouterType.FORWARD);
        Optional<User> userOptional;
        User user = null;
        try {
            userOptional = dao.getEntityById((String) request.getAttribute("email"));
            if(userOptional.isPresent()){
                String password = userOptional.get().getPass();
                if(password.equals(request.getAttribute("pass"))){
                    user = userOptional.get();
                    sessionServic.addUser(request,user);
                }
                else{
                    router.setPagePath(JspPath.SIGN_IN);
                }
            }
            else{
                router.setPagePath(JspPath.SIGN_IN);
            }
        } catch (DataBaseException e) {
            // TODO: 1/30/2022
        }
        return router;
    }
}
