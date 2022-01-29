package by.ishangulyev.application.service;

import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.controller.command.LanguageType;
import by.ishangulyev.application.validator.CookieValidator;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

public class LanguageService {
    private final String RU_PATH = "src/main/resources/language/russian.properties";
    private final String EN_PATH = "src/main/resources/language/english.properties";
    private final SetPage setPage = new SetPage();

    public Cookie createCookie(LanguageType type){
        Cookie language = null;
        switch (type){
            case RU -> {
                language = new Cookie("language","ru");
            }
            case EN -> {
                language = new Cookie("language","en");
            }
        }
        return language;
    }
    public void setLanguageAtPage(HttpServletRequest request, JspPath jsp){
        switch (jsp){
            case INDEX -> {
                setPage.setIndex(request);
            }
            case SIGN_IN -> {
                setPage.setSignIn(request);
            }
            case SIGN_UP -> {
                setPage.setSignUp(request);
            }
            case GADGETS -> {
                setPage.setGadgets(request);
            }
            case USERS -> {
                setPage.setUsers(request);
            }
            case ERROR4XX -> {
                setPage.setError400(request);
            }
            case ERROR5XX -> {
                setPage.setError500(request);
            }
        }
    }

    private Properties getProperty(String path,JspPath jsp){
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream(path)) {
            prop.load(input);
        } catch (IOException ex) {

        }
        return prop;
    }
}
