package by.ishangulyev.application.service;

import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.command.LanguageType;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LanguageServiceHelper {
    private final String RU_PATH = "language/russian.properties";
    private final String EN_PATH = "language/english.properties";

    public void setIndex(HttpServletRequest request, Router router){
        Properties properties = getProperty(router);
        request.setAttribute("title",properties.getProperty("index.title"));
    }
    public void setSignUp(HttpServletRequest request, Router router){
        Properties properties = getProperty(router);
    }
    public void setSignIn(HttpServletRequest request, Router router){
        Properties properties = getProperty(router);
    }
    public void setError500(HttpServletRequest request, Router router){
        Properties properties = getProperty(router);
    }
    public void setError400(HttpServletRequest request, Router router){
        Properties properties = getProperty(router);
    }
    public void setGadgets(HttpServletRequest request, Router router){
        Properties properties = getProperty(router);
    }
    public void setUsers(HttpServletRequest request, Router router){
        Properties properties = getProperty(router);
    }
    public void setAccount(HttpServletRequest request, Router router) {
        Properties properties = getProperty(router);
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
