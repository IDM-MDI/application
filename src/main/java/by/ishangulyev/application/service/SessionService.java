package by.ishangulyev.application.service;

import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.model.entity.impl.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SessionService {
    private CookieService cookieService = new CookieService();

    public void sessionHandler(HttpServletRequest request, HttpServletResponse response,JspPath path){
       HttpSession session = request.getSession(true);
       if(session.isNew()){
           Cookie[] cookies = request.getCookies();
           cookieService.cookieHandler(request,response,path);
           initSession(session,cookies);
       }
    }

    public void initSession(HttpSession session,Cookie[] cookies){
        session.setAttribute("user",null);
        session.setMaxInactiveInterval(1800);
        cookieService.getCookie(cookies,"pass");
        cookieService.getCookie(cookies,"email");
    }
    public void addUser(HttpServletRequest request,User user){
        request.getSession().setAttribute("user",user);
    }
}
