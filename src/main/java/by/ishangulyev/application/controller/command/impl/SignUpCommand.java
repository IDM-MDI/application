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
import by.ishangulyev.application.validator.UserValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.Date;

public class SignUpCommand implements ActionCommand {
    private DaoUser registration;
    private DaoCart cart;

    public SignUpCommand(){
        registration = new DaoUser();
        cart = new DaoCart();
    }

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router = new Router(JspPath.SIGN_IN, RouterType.FORWARD);
        UserValidator validator = UserValidator.getInstance();
        User user = fillEntityInfo(request);
        if(validator.isUserValid(user)){
            registration.create(user);
            Cart userCart = new Cart();
            userCart.setUserID(user.getEmail());
            cart.create(userCart);
        }
        else{
            router.setPagePath(JspPath.SIGN_UP.getValue());
        }
        return router;
    }

    private User fillEntityInfo(HttpServletRequest req){
        User result = new User();
        User user = new User();
        user.setName((String) req.getAttribute("name"));
        user.setEmail((String) req.getAttribute("login"));
        user.setPass((String) req.getAttribute("password"));
        user.setRole(Role.USER);
        user.setDate(new Date(System.currentTimeMillis()));
        return result;
    }
}
