package by.ishangulyev.application.service;

import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.command.LanguageType;
import by.ishangulyev.application.model.entity.impl.User;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LanguageServiceHelper {
    private final String RU_PATH = "language/russian.properties";
    private final String EN_PATH = "language/english.properties";

    public void setIndex(HttpServletRequest request, Router router){
        Properties properties = getProperty(router);
        setHeader(request,properties);
        request.setAttribute("titleTranslate",properties.getProperty("index.title"));
    }
    public void setSignUp(HttpServletRequest request, Router router){
        Properties properties = getProperty(router);
        setHeader(request,properties);
    }
    public void setSignIn(HttpServletRequest request, Router router){
        Properties properties = getProperty(router);
        setHeader(request,properties);
    }
    public void setError500(HttpServletRequest request, Router router){
        Properties properties = getProperty(router);
        setHeader(request,properties);
    }
    public void setError400(HttpServletRequest request, Router router){
        Properties properties = getProperty(router);
        setHeader(request,properties);
    }
    public void setGadgets(HttpServletRequest request, Router router){
        Properties properties = getProperty(router);
        setHeader(request,properties);
    }
    public void setUsers(HttpServletRequest request, Router router){
        Properties properties = getProperty(router);
        setHeader(request,properties);
    }
    public void setAccount(HttpServletRequest request, Router router) {
        Properties properties = getProperty(router);
        setHeader(request,properties);
    }
    private void setGuestHeader(HttpServletRequest request, Properties properties) {
        request.setAttribute("signinTranslate",properties.getProperty("header.signin"));
        request.setAttribute("signupTranslate",properties.getProperty("header.signup"));
        request.setAttribute("gadgetsTranslate",properties.getProperty("header.gadgets"));
        request.setAttribute("searchTranslate",properties.getProperty("header.search"));
    }
    private void setUserHeader(HttpServletRequest request, Properties properties){
        request.setAttribute("gadgetsTranslate",properties.getProperty("headerUser.gadgets"));
        request.setAttribute("searchTranslate",properties.getProperty("headerUser.search"));

    }
    private void setHeader(HttpServletRequest request, Properties properties){
        User user = (User) request.getSession().getAttribute("user");
        if(user.getRole() == null){
            setGuestHeader(request,properties);
        }
        else{
            setUserHeader(request,properties);
        }
    }
    private Properties getProperty(Router router){
        String path;
        Properties prop = new Properties();
        switch (router.getLanguage()){
            case EN -> path = EN_PATH;
            default -> path = RU_PATH;
        }
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(path)) {
            prop.load(input);
        } catch (IOException e) {

        }
        return prop;
    }

}
