package by.ishangulyev.application.service;

import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.util.AttributeUtil;
import jakarta.servlet.http.HttpServletRequest;

public class LanguageService {
    private static LanguageService instance = new LanguageService();
    private final AttributeUtil languageServiceHelper = new AttributeUtil();

    private LanguageService(){}

    public static LanguageService getInstance() {
        return instance;
    }
    public void setLanguageAtPage(HttpServletRequest request, Router router){
        switch (router.getPagePath()){
            case INDEX -> languageServiceHelper.setIndex(request,router);
            case SIGN_IN -> languageServiceHelper.setSignIn(request,router);
            case SIGN_UP -> languageServiceHelper.setSignUp(request,router);
            case GADGETS -> languageServiceHelper.setGadgets(request,router);
            case ERROR400 -> languageServiceHelper.setError400(request,router);
            case ERROR500 -> languageServiceHelper.setError500(request,router);
            case ACCOUNT -> languageServiceHelper.setAccount(request,router);
            case CART -> languageServiceHelper.setCart(request,router);
            case SETTINGS -> languageServiceHelper.setSettings(request,router);
            case BATTERY_SETTINGS -> languageServiceHelper.setBatterySettings(request,router);
            case USER_SETTINGS -> languageServiceHelper.setUserSettings(request,router);
            case AUDIO_SETTINGS -> languageServiceHelper.setAudioSettings(request,router);
            case CPU_SETTINGS -> languageServiceHelper.setCpuSettings(request,router);
            case VIDEO_SETTINGS -> languageServiceHelper.setVideoSettings(request,router);
            case GADGET_SETTINGS -> languageServiceHelper.setGadgetSettings(request,router);
            case MEMORY_SETTINGS -> languageServiceHelper.setMemorySettings(request,router);
            case CATEGORY_SETTINGS -> languageServiceHelper.setCategorySettings(request,router);
            case ABOUT -> languageServiceHelper.setAbout(request,router);
        }
    }
}
