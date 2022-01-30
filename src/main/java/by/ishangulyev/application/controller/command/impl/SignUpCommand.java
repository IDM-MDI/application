package by.ishangulyev.application.controller.command.impl;

import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.RouterType;
import by.ishangulyev.application.controller.command.ActionCommand;
import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.dao.impl.DaoCart;
import by.ishangulyev.application.dao.impl.DaoUser;
import by.ishangulyev.application.model.entity.impl.Cart;
import by.ishangulyev.application.model.entity.impl.Role;
import by.ishangulyev.application.model.entity.impl.User;
import by.ishangulyev.application.util.HashPassGenerator;
import by.ishangulyev.application.validator.UserValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.security.NoSuchAlgorithmException;
import java.sql.Date;

public class SignUpCommand implements ActionCommand {
    private DaoUser registration;
    private DaoCart cart;
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router = new Router(JspPath.SIGN_IN, RouterType.FORWARD);
        UserValidator validator = UserValidator.getInstance();
        User user = fillEntityInfo(request);
        if(validator.isUserValid(user)){
            registration = new DaoUser();
            cart = new DaoCart();
            try {
                user.setPass(HashPassGenerator.generate(user.getPass()));
            } catch (NoSuchAlgorithmException e) {
                // TODO: 1/30/2022  
            }
            registration.create(user);
            Cart userCart = new Cart();
            userCart.setUserID(user.getEmail());
            cart.create(userCart);
        }
        else{
            router.setPagePath(JspPath.SIGN_UP);
        }
        return router;
    }

    private User fillEntityInfo(HttpServletRequest req){
        User result = new User();
        result.setName((String) req.getParameter("name"));
        result.setEmail((String) req.getParameter("email"));
        result.setPass((String) req.getParameter("password"));
        result.setRole(Role.USER);
        result.setDate(new Date(System.currentTimeMillis()));
        return result;
    }
}
