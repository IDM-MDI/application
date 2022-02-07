package by.ishangulyev.application.service;

import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.command.LanguageType;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class LanguageService {
    private final AttributeService languageServiceHelper = new AttributeService();

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
            case SETTINGS -> languageServiceHelper.setSettings(request,router);
            case BATTERYSETTINGS -> languageServiceHelper.setBatterySettings(request,router);
            case USERSETTINGS -> languageServiceHelper.setUserSettings(request,router);
        }
    }
}
