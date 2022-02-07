package by.ishangulyev.application.service;

import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.dao.impl.DaoCart;
import by.ishangulyev.application.dao.impl.DaoUser;
import by.ishangulyev.application.exception.DaoException;
import by.ishangulyev.application.exception.DataBaseException;
import by.ishangulyev.application.model.entity.impl.Cart;
import by.ishangulyev.application.model.entity.impl.Role;
import by.ishangulyev.application.model.entity.impl.User;
import by.ishangulyev.application.util.HashPassGenerator;
import by.ishangulyev.application.validator.UserValidator;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.Optional;

public class UserService {
    private static final Logger logger = LogManager.getLogger();
    private static UserService instance = new UserService();
    private DaoUser registration;
    private DaoCart cart;


    private UserService(){}

    public static UserService getInstance() {
        return instance;
    }

    public User login(String email,String pass){
        User result = null;
        DaoUser dao = new DaoUser();
        Optional<User> userOptional;
        try {
            userOptional = dao.findEntityById(email);
            if(userOptional.isPresent()){
                String password = userOptional.get().getPass();
                if(password.equals(HashPassGenerator.generate(pass))){
                    result = userOptional.get();
                }

            }
        } catch (DaoException e) {
            logger.log(Level.ERROR,"Something go wrong with Dao User");
        }
        return result;
    }

    public boolean registration(String email,String pass){
        boolean result;
        UserValidator validator = UserValidator.getInstance();
        User user = fillEntityInfo(email,pass);
        if(validator.isUserValid(user)){
            registration = new DaoUser();
            cart = new DaoCart();
            user.setPass(HashPassGenerator.generate(user.getPass()));
            registration.create(user);
            Cart userCart = new Cart();
            userCart.setUserID(user.getEmail());
            cart.create(userCart);
            result = true;
            logger.log(Level.INFO,"User was add");
        }
        else{
            result = false;
            logger.log(Level.INFO,"User not valid");
        }
        return result;
    }

    private User fillEntityInfo(String email,String pass){
        User result = new User();
        result.setEmail(email);
        result.setPass(pass);
        result.setRole(Role.USER);
        result.setDate(new Date(System.currentTimeMillis()));
        return result;
    }
}
