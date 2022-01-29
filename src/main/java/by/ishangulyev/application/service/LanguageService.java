package by.ishangulyev.application.service;

import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.controller.command.LanguageType;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LanguageService {
    private final String RU_PATH = "src/main/resources/language/russian.properties";
    private final String EN_PATH = "src/main/resources/language/english.properties";
    private final LanguageServiceHelper languageServiceHelper = new LanguageServiceHelper();

    public Cookie createCookie(LanguageType type){
        Cookie language = null;
        switch (type){
            case RU -> {
                language = new Cookie("language","RU");
            }
            case EN -> {
                language = new Cookie("language","EN");
            }
        }
        return language;
    }
    public void setLanguageAtPage(HttpServletRequest request, Router router){
        switch (router.getPagePath()){
            case INDEX -> {
                languageServiceHelper.setIndex(request);
            }
            case SIGN_IN -> {
                languageServiceHelper.setSignIn(request);
            }
            case SIGN_UP -> {
                languageServiceHelper.setSignUp(request);
            }
            case GADGETS -> {
                languageServiceHelper.setGadgets(request);
            }
            case USERS -> {
                languageServiceHelper.setUsers(request);
            }
            case ERROR4XX -> {
                languageServiceHelper.setError400(request);
            }
            case ERROR5XX -> {
                languageServiceHelper.setError500(request);
            }
        }
    }

    private Properties getProperty(String path,JspPath jsp){
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream(path)) {
            prop.load(input);
        } catch (IOException e) {

        }
        return prop;
    }
}
