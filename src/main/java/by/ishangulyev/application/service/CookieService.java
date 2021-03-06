package by.ishangulyev.application.service;

import by.ishangulyev.application.controller.AttributeName;
import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.controller.command.LanguageType;
import by.ishangulyev.application.dao.impl.DaoUser;
import by.ishangulyev.application.exception.DaoException;
import by.ishangulyev.application.exception.DataBaseException;
import by.ishangulyev.application.model.entity.impl.User;
import by.ishangulyev.application.validator.CookieValidator;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class CookieService {
    private static final Logger logger = LogManager.getLogger();
    private static CookieService instance = new CookieService();
    private CookieValidator validator = CookieValidator.getInstance();
    private UserService userService;
    private CookieService(){}

    public static CookieService getInstance() {
        return instance;
    }

    public void cookieHandler(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        if(cookies == null){
            response.addCookie(createCookie(LanguageType.RU));
            response.addCookie(new Cookie(AttributeName.USER_EMAIL,null));
            response.addCookie(new Cookie(AttributeName.PASS,null));
        }
    }
    public Cookie getCookie(Cookie[] cookies,String name){
        Cookie result = null;
        for (Cookie i:cookies) {
            if(i.getName().equals(name)){
                result = i;
            }
        }
        return result;
    }
    public User findUser(Cookie[] cookies){
        User result;
        if(cookies != null){
            if(validator.isLoginValid(cookies)){
                userService = UserService.getInstance();
                String email = getCookie(cookies,AttributeName.USER_EMAIL).getValue();
                String pass = getCookie(cookies,AttributeName.PASS).getValue();
                result = userService.login(email,pass);
                if(result == null){
                    result = new User();
                }
            }
            else{
                result= new User();
            }
        }
        else{
            result= new User();
        }
        return result;
    }
    public LanguageType findLanguage(Cookie[] cookies){
        LanguageType type = LanguageType.RU;
        if(cookies != null){
            if(validator.isCookieExist(cookies,AttributeName.LANGUAGE)){
                String language = getCookie(cookies,AttributeName.LANGUAGE).getValue();
                type = LanguageType.valueOf(language);
            }
        }
        return type;
    }


    public void addLanguage(HttpServletRequest request,HttpServletResponse response, String language) {
        Cookie cookie = getCookie(request.getCookies(),AttributeName.LANGUAGE);
        cookie.setMaxAge(60 * 60 * 24 * 365 * 10);
        cookie.setValue(language.toUpperCase());
        response.addCookie(cookie);
    }
    public void addUser(HttpServletRequest request,HttpServletResponse response,User user) {
        if(!(user.getEmail() == null && user.getEmail().isEmpty()
        && user.getPass() == null && user.getPass().isEmpty())) {
            Cookie email = getCookie(request.getCookies(),AttributeName.USER_EMAIL);
            Cookie password = getCookie(request.getCookies(),AttributeName.PASS);
            if(email != null && password != null){
                email.setValue(user.getEmail());
                password.setValue(user.getPass());
            }
            else{
                email = new Cookie(AttributeName.USER_EMAIL,user.getEmail());
                password = new Cookie(AttributeName.PASS,user.getPass());
            }
            email.setMaxAge(60 * 60 * 24 * 365 * 10);
            password.setMaxAge(60 * 60 * 24 * 365 * 10);
            response.addCookie(email);
            response.addCookie(password);
        }
    }
    public void removeUser(HttpServletRequest request,HttpServletResponse response){
        Cookie email = getCookie(request.getCookies(),AttributeName.USER_EMAIL);
        Cookie password = getCookie(request.getCookies(),AttributeName.PASS);
        email.setValue(null);
        password.setValue(null);
        response.addCookie(email);
        response.addCookie(password);
    }

    public Cookie createCookie(LanguageType type){
        Cookie language = null;
        switch (type){
            case RU -> {
                language = new Cookie(AttributeName.LANGUAGE,"RU");
            }
            case EN -> {
                language = new Cookie(AttributeName.LANGUAGE,"EN");
            }
        }
        return language;
    }
}
