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
    }

    private Map<String,String> getProperties(String path,JspPath jsp){
        Map<String,String> result = new HashMap<>();
        try (InputStream input = new FileInputStream(path)) {
            Properties prop = new Properties();

            // load a properties file
            prop.load(input);
            prop.getProperty(jsp.name().toLowerCase() + "_title");


        } catch (IOException ex) {
            //ex.printStackTrace();
        }
        return result;
    }
}
