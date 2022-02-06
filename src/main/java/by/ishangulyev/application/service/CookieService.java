package by.ishangulyev.application.service;

import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.controller.command.LanguageType;
import by.ishangulyev.application.dao.impl.DaoUser;
import by.ishangulyev.application.exception.DataBaseException;
import by.ishangulyev.application.model.entity.impl.User;
import by.ishangulyev.application.validator.CookieValidator;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Optional;

public class CookieService {
    private LanguageService languageService;
    private PasswordHashService passwordHashService;
    private CookieValidator validator;
    private LanguageType languageType;
    private User user;

    public CookieService(){
        languageService = new LanguageService();
        passwordHashService = new PasswordHashService();
        validator = new CookieValidator();
    }

    public void cookieHandler(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        if(cookies == null){
            response.addCookie(languageService.createCookie(LanguageType.RU));
            languageType = LanguageType.RU;
            this.user = new User();
            response.addCookie(new Cookie("email",null));
            response.addCookie(new Cookie("pass",null));
        }
        else {
            if(validator.isCookieExist(cookies,"language")){
                String language = getCookie(cookies,"language").getValue();
                this.languageType = LanguageType.valueOf(language);
            }
            if(validator.isLoginValid(cookies)){
                this.user = findUser(cookies);
            }
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
    private User findUser(Cookie[] cookies){
        DaoUser dao = new DaoUser();
        Optional<User> userOptional;
        User result = null;
        try {
            userOptional = dao.findEntityById(getCookie(cookies,"email").getValue());
            if(userOptional.isPresent()){
                String password = userOptional.get().getPass();
                if(password.equals(getCookie(cookies,"pass").getValue())){
                    result = userOptional.get();
                }
            }
        } catch (DataBaseException e) {
            // TODO: 1/30/2022  
        }
        return result;
    }

    public User getUser() {
        return user;
    }

    public LanguageType getLanguageType() {
        return languageType;
    }

    public void addLanguage(HttpServletRequest request,HttpServletResponse response, String language) {
        Cookie cookie = getCookie(request.getCookies(),"language");
        cookie.setMaxAge(60 * 60 * 24 * 365 * 10);
        cookie.setValue(language.toUpperCase());
        response.addCookie(cookie);
    }
    public void addUser(HttpServletRequest request,HttpServletResponse response,User user) {
        if((user.getEmail() == null && user.getEmail().isEmpty()
        && user.getPass() == null && user.getPass().isEmpty())) {
            Cookie email = getCookie(request.getCookies(),"email");
            Cookie password = getCookie(request.getCookies(),"pass");
            email.setMaxAge(60 * 60 * 24 * 365 * 10);
            password.setMaxAge(60 * 60 * 24 * 365 * 10);
            email.setValue(user.getEmail());
            password.setValue(user.getPass());
            response.addCookie(email);
            response.addCookie(password);
        }
    }
    public void removeUser(HttpServletRequest request,HttpServletResponse response){
        Cookie email = getCookie(request.getCookies(),"email");
        Cookie password = getCookie(request.getCookies(),"pass");
        email.setValue(null);
        password.setValue(null);
        response.addCookie(email);
        response.addCookie(password);
    }

}
