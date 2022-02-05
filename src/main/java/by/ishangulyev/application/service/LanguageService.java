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
            case INDEX -> languageServiceHelper.setIndex(request,router);
            case SIGN_IN -> languageServiceHelper.setSignIn(request,router);
            case SIGN_UP -> languageServiceHelper.setSignUp(request,router);
            case GADGETS -> languageServiceHelper.setGadgets(request,router);
            case USERS -> languageServiceHelper.setUsers(request,router);
            case ERROR4XX -> languageServiceHelper.setError400(request,router);
            case ERROR5XX -> languageServiceHelper.setError500(request,router);
            case ACCOUNT -> languageServiceHelper.setAccount(request,router);
            case CART -> languageServiceHelper.setCart(request,router);
            case ADDGADGET -> languageServiceHelper.setAddGadget(request,router);
            case UPDATEUSER -> languageServiceHelper.setUpdateUser(request,router);
            case UPDATEGADGET -> languageServiceHelper.setUpdateGadget(request,router);
        }
    }
}
