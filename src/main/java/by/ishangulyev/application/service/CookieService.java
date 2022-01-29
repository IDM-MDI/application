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

    public CookieService(){
        languageService = new LanguageService();
        passwordHashService = new PasswordHashService();
        validator = new CookieValidator();
    }

    public void cookieHandler(HttpServletRequest request, HttpServletResponse response, JspPath jsp){
        Cookie[] cookies = request.getCookies();
        if(validator.isCookieExist(cookies,"language")){
            languageService.setLanguageAtPage(request,jsp);
        }
        else{
            response.addCookie(languageService.createCookie(LanguageType.RU));
        }

        if(validator.isLoginValid(cookies)){
            User user = findUser(cookies);
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
        DaoUser dao = new DaoUser();
        Optional<User> userOptional;
        User result = null;
        try {
            userOptional = dao.getEntityById(getCookie(cookies,"email").getValue());
            if(userOptional.isPresent()){
                String password = userOptional.get().getPass();
                if(password.equals(getCookie(cookies,"pass").getValue())){
                    result = userOptional.get();
                }
            }
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
        return result;
    }
}
