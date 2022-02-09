package by.ishangulyev.application.service;

import by.ishangulyev.application.controller.AttributeName;
import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.controller.command.LanguageType;
import by.ishangulyev.application.model.entity.impl.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SessionService {
    private static final Logger logger = LogManager.getLogger();
    private static SessionService instance = new SessionService();
    private CookieService cookieService = CookieService.getInstance();
    private CartService cartService = CartService.getInstance();

    private SessionService(){}

    public static SessionService getInstance() {
        return instance;
    }

    public HttpSession sessionHandler(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(true);
        LanguageType language = (LanguageType) session.getAttribute(AttributeName.LANGUAGE);
       if(language == null){
           cookieService.cookieHandler(request,response);
           initSession(session,request.getCookies());
       }
       return session;
    }

    public void initSession(HttpSession session,Cookie[] cookies){
        User user = cookieService.findUser(cookies);
        session.setAttribute(AttributeName.USER,user);
        session.setAttribute(AttributeName.CART,cartService.findUserCart(user));
        session.setMaxInactiveInterval(1000);
        LanguageType type = cookieService.findLanguage(cookies);
        session.setAttribute(AttributeName.LANGUAGE,type);
    }
    public void addUser(HttpSession session,User user){
        session.setAttribute(AttributeName.USER,user);
    }
    public void addLanguage(HttpSession session, LanguageType type){
        session.setAttribute(AttributeName.LANGUAGE,type);
    }
    public void removeUser(HttpSession session){
        session.removeAttribute(AttributeName.USER);
    }

    public void updateUser(HttpSession session, User update) {
        User user = (User) session.getAttribute(AttributeName.USER);
        if(!(update.getName() == null || update.getName().isEmpty())){
            user.setName(update.getName());
        }
        if(!(update.getPass() == null || update.getPass().isEmpty())){
            user.setPass(update.getPass());
        }
        if(!(update.getPhoto() == null)){
            user.setPhoto(update.getPhoto());
            user.setPhotoToString();
        }
        session.setAttribute(AttributeName.USER,user);
    }
}
