package by.ishangulyev.application.service;

import by.ishangulyev.application.controller.AttributeName;
import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.RouterType;
import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.dao.impl.DaoCart;
import by.ishangulyev.application.dao.impl.DaoUser;
import by.ishangulyev.application.dao.impl.DaoVideo;
import by.ishangulyev.application.exception.DaoException;
import by.ishangulyev.application.exception.DataBaseException;
import by.ishangulyev.application.model.entity.impl.Cart;
import by.ishangulyev.application.model.entity.impl.Role;
import by.ishangulyev.application.model.entity.impl.User;
import by.ishangulyev.application.model.entity.impl.Video;
import by.ishangulyev.application.util.HashPassGenerator;
import by.ishangulyev.application.validator.UserValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class UserService {
    private static final Logger logger = LogManager.getLogger();
    private static UserService instance = new UserService();
    private UserValidator validator = UserValidator.getInstance();
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
                if(password.equals(HashPassGenerator.generate(pass)) || password.equals(pass)){
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

    public User updateAccount(String email, String password,String username, String role, Part photo){
        DaoUser daoUser = new DaoUser();
        User user = new User();
        if(email != null){
            user.setEmail(email);
        }
        if(password != null || !password.isBlank()){
            user.setPass(HashPassGenerator.generate(password));
        }
        if(username != null|| !password.isBlank()){
            user.setName(username);
        }
        if(role != null){
            user.setRole(Role.valueOf(role.toUpperCase()));
        }
        if(photo != null){
            try {
                if(validator.isPhotoValid(photo))
                user.setPhoto(photo.getInputStream().readAllBytes());
            } catch (IOException e) {
                user.setPhoto(null);
            }
        }
        daoUser.update(user);
        return user;
    }

    public Router getAccounts(HttpServletRequest request){
        String page = request.getParameter(AttributeName.PAGE);
        if(page == null || page.isEmpty()){
            page = "1";
        }
        int pageNumber = Integer.parseInt(page);
        int next = 0,prev = pageNumber-1;
        List<User> userList = null;
        DaoUser daoUser = new DaoUser();
        try {
            pageNumber--;
            userList = daoUser.findByCount(pageNumber);
            if(userList.size() > 9){
                next = pageNumber+1;
                userList.remove(userList.size()-1);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR,"Error getting all Users");
        }
        request.setAttribute("userList",userList);
        request.setAttribute("currentPage",++pageNumber);
        request.setAttribute("nextPage",next);
        request.setAttribute("prevPage",prev);

        return new Router(JspPath.USER_SETTINGS, RouterType.FORWARD);
    }

    public boolean deleteAccount(String email) {
        DaoUser daoUser = new DaoUser();
        DaoCart daoCart = new DaoCart();
        daoCart.deleteByEmail(email);
        return daoUser.delete(email);
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
