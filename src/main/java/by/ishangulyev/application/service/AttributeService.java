package by.ishangulyev.application.service;

import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.command.LanguageType;
import by.ishangulyev.application.model.entity.impl.Role;
import by.ishangulyev.application.model.entity.impl.User;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AttributeService {
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
        request.setAttribute("titleTranslate",properties.getProperty("signup.title"));
    }
    public void setSignIn(HttpServletRequest request, Router router){
        Properties properties = getProperty(router);
        setHeader(request,properties);
        request.setAttribute("titleTranslate",properties.getProperty("signin.title"));
    }
    public void setError500(HttpServletRequest request, Router router){
        Properties properties = getProperty(router);
        setHeader(request,properties);
        request.setAttribute("titleTranslate",properties.getProperty("error5xx.title"));
    }
    public void setError400(HttpServletRequest request, Router router){
        Properties properties = getProperty(router);
        setHeader(request,properties);
        request.setAttribute("titleTranslate",properties.getProperty("error4xx.title"));
    }
    public void setGadgets(HttpServletRequest request, Router router){
        Properties properties = getProperty(router);
        setHeader(request,properties);
        request.setAttribute("titleTranslate",properties.getProperty("gadgets.title"));
    }
    public void setUsers(HttpServletRequest request, Router router){
        Properties properties = getProperty(router);
        setHeader(request,properties);
        request.setAttribute("titleTranslate",properties.getProperty("users.title"));
    }
    public void setAccount(HttpServletRequest request, Router router) {
        Properties properties = getProperty(router);
        setHeader(request,properties);
        request.setAttribute("titleTranslate",properties.getProperty("account.title"));
    }
    public void setCart(HttpServletRequest request, Router router) {
        Properties properties = getProperty(router);
        setHeader(request,properties);
        request.setAttribute("titleTranslate",properties.getProperty("cart.title"));
    }
    public void setBatterySettings(HttpServletRequest request, Router router){
        Properties properties = getProperty(router);
        setHeader(request,properties);
        request.setAttribute("titleTranslate",properties.getProperty("settings.battery"));
    }
    public void setUserSettings(HttpServletRequest request, Router router) {
        Properties properties = getProperty(router);
        setHeader(request,properties);
        request.setAttribute("titleTranslate",properties.getProperty("settings.user"));
    }
    public void setAudioSettings(HttpServletRequest request, Router router){
        Properties properties = getProperty(router);
        setHeader(request,properties);
        request.setAttribute("titleTranslate",properties.getProperty("settings.audio"));
    }
    public void setVideoSettings(HttpServletRequest request, Router router){
        Properties properties = getProperty(router);
        setHeader(request,properties);
        request.setAttribute("titleTranslate",properties.getProperty("settings.video"));
    }
    public void setGadgetSettings(HttpServletRequest request, Router router){
        Properties properties = getProperty(router);
        setHeader(request,properties);
        request.setAttribute("titleTranslate",properties.getProperty("settings.gadget"));
    }
    public void setCpuSettings(HttpServletRequest request, Router router){
        Properties properties = getProperty(router);
        setHeader(request,properties);
        request.setAttribute("titleTranslate",properties.getProperty("settings.cpu"));
    }
    public void setMemorySettings(HttpServletRequest request, Router router){
        Properties properties = getProperty(router);
        setHeader(request,properties);
        request.setAttribute("titleTranslate",properties.getProperty("settings.memory"));
    }
    public void setCategorySettings(HttpServletRequest request, Router router){
        Properties properties = getProperty(router);
        setHeader(request,properties);
        request.setAttribute("titleTranslate",properties.getProperty("settings.category"));
    }
    public void setSettings(HttpServletRequest request, Router router) {
        Properties properties = getProperty(router);
        setHeader(request,properties);
        request.setAttribute("titleTranslate",properties.getProperty("settings.title"));
        request.setAttribute("audioTranslate",properties.getProperty("settings.audio"));
        request.setAttribute("videoTranslate",properties.getProperty("settings.video"));
        request.setAttribute("cpuTranslate",properties.getProperty("settings.cpu"));
        request.setAttribute("categoryTranslate",properties.getProperty("settings.category"));
        request.setAttribute("gadgetTranslate",properties.getProperty("settings.gadget"));
        request.setAttribute("userTranslate",properties.getProperty("settings.user"));
        request.setAttribute("memoryTranslate",properties.getProperty("settings.memory"));
        request.setAttribute("batteryTranslate",properties.getProperty("settings.battery"));
    }

    public void setAddGadget(HttpServletRequest request, Router router) {
        Properties properties = getProperty(router);
        setHeader(request,properties);
        request.setAttribute("titleTranslate",properties.getProperty("addGadget.title"));
    }


    private void setGuestHeader(HttpServletRequest request, Properties properties) {
        request.setAttribute("signinTranslate",properties.getProperty("header.signin"));
        request.setAttribute("signupTranslate",properties.getProperty("header.signup"));
        request.setAttribute("gadgetsTranslate",properties.getProperty("header.gadgets"));
        request.setAttribute("searchTranslate",properties.getProperty("header.search"));
        request.setAttribute("languageTranslate",properties.getProperty("header.language"));
    }
    private void setUserHeader(HttpServletRequest request, Properties properties){
        request.setAttribute("gadgetsTranslate",properties.getProperty("headerUser.gadgets"));
        request.setAttribute("searchTranslate",properties.getProperty("headerUser.search"));
        request.setAttribute("profileTranslate",properties.getProperty("headerUser.profile"));
        request.setAttribute("settingsTranslate",properties.getProperty("headerUser.settings"));
        request.setAttribute("signoutTranslate",properties.getProperty("headerUser.signout"));
        request.setAttribute("languageTranslate",properties.getProperty("header.language"));
    }
    private void setHeader(HttpServletRequest request, Properties properties){
        User user = (User) request.getSession().getAttribute("user");
        if(user == null || user.getRole() == null){
            setGuestHeader(request,properties);
        }
        else{
            setUserHeader(request,properties);
        if(!(user.getPhotoToString() == null || user.getPhotoToString().isEmpty())){
                request.setAttribute("avatar",user.getPhotoToString());
            }
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
