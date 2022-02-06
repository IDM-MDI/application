package by.ishangulyev.application.service;

import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.controller.command.LanguageType;
import by.ishangulyev.application.model.entity.impl.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SessionService {
    private CookieService cookieService = new CookieService();
    private LanguageType type;

    public HttpSession sessionHandler(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(true);
        LanguageType language = (LanguageType) session.getAttribute("language");
       if(language == null){
           cookieService.cookieHandler(request,response);
           initSession(session);
       }
       return session;
    }

    public void initSession(HttpSession session){
        session.setAttribute("user",cookieService.getUser());
        session.setMaxInactiveInterval(1000);
        this.type = cookieService.getLanguageType();
        session.setAttribute("language",type);
    }
    public void addUser(HttpServletRequest request,User user){
        request.getSession().setAttribute("user",user);
    }
    public void removeUser(HttpServletRequest request){
        request.getSession().removeAttribute("user");
    }
    public void addLanguage(HttpServletRequest request, LanguageType type){
        request.getSession().setAttribute("language",type);
        this.type = type;
    }

    public LanguageType getType() {
        return type;
    }

    public void updateUser(HttpServletRequest request, User update) {
        User user = (User) request.getSession().getAttribute("user");
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
        request.getSession().setAttribute("user",user);
    }
}
