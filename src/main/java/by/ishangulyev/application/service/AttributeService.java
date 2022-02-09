package by.ishangulyev.application.service;

import by.ishangulyev.application.controller.Router;
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
        setUserProperties(request,properties);
    }
    public void setSignIn(HttpServletRequest request, Router router){
        Properties properties = getProperty(router);
        setHeader(request,properties);
        request.setAttribute("titleTranslate",properties.getProperty("signin.title"));
        setUserProperties(request,properties);
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
        setSettingsProperties(request,properties);
        setGadgetProperties(request,properties);
    }
    public void setAccount(HttpServletRequest request, Router router) {
        Properties properties = getProperty(router);
        setHeader(request,properties);
        request.setAttribute("titleTranslate",properties.getProperty("account.title"));
        setUserProperties(request,properties);
    }
    public void setCart(HttpServletRequest request, Router router) {
        Properties properties = getProperty(router);
        setHeader(request,properties);
        request.setAttribute("titleTranslate",properties.getProperty("cart.title"));
        setSettingsProperties(request,properties);
        setGadgetProperties(request,properties);
    }



    public void setBatterySettings(HttpServletRequest request, Router router){
        Properties properties = getProperty(router);
        setHeader(request,properties);
        request.setAttribute("titleTranslate",properties.getProperty("settings.battery"));
        setSettingsProperties(request,properties);
        request.setAttribute("mahTranslate",properties.getProperty("mah"));
    }
    public void setUserSettings(HttpServletRequest request, Router router) {
        Properties properties = getProperty(router);
        setHeader(request,properties);
        request.setAttribute("titleTranslate",properties.getProperty("settings.user"));
        setSettingsProperties(request,properties);
        setUserProperties(request,properties);
    }
    public void setAudioSettings(HttpServletRequest request, Router router){
        Properties properties = getProperty(router);
        setHeader(request,properties);
        request.setAttribute("titleTranslate",properties.getProperty("settings.audio"));
        setSettingsProperties(request,properties);
        request.setAttribute("freqTranslate",properties.getProperty("frequency"));
        request.setAttribute("typeTranslate",properties.getProperty("type"));
    }
    public void setVideoSettings(HttpServletRequest request, Router router){
        Properties properties = getProperty(router);
        setHeader(request,properties);
        request.setAttribute("titleTranslate",properties.getProperty("settings.video"));
        setSettingsProperties(request,properties);
        request.setAttribute("resolTranslate",properties.getProperty("resol"));
        request.setAttribute("ratioTranslate",properties.getProperty("ratio"));
        request.setAttribute("britTranslate",properties.getProperty("brit"));
        request.setAttribute("typeTranslate",properties.getProperty("type"));
    }
    public void setGadgetSettings(HttpServletRequest request, Router router){
        Properties properties = getProperty(router);
        setHeader(request,properties);
        request.setAttribute("titleTranslate",properties.getProperty("settings.gadget"));
        setSettingsProperties(request,properties);
        setGadgetProperties(request,properties);
    }
    public void setCpuSettings(HttpServletRequest request, Router router){
        Properties properties = getProperty(router);
        setHeader(request,properties);
        request.setAttribute("titleTranslate",properties.getProperty("settings.cpu"));
        setSettingsProperties(request,properties);
        request.setAttribute("coreTranslate",properties.getProperty("core"));
        request.setAttribute("freqTranslate",properties.getProperty("frequency"));
        request.setAttribute("bitTranslate",properties.getProperty("bit"));
    }
    public void setMemorySettings(HttpServletRequest request, Router router){
        Properties properties = getProperty(router);
        setHeader(request,properties);
        request.setAttribute("titleTranslate",properties.getProperty("settings.memory"));
        setSettingsProperties(request,properties);
        request.setAttribute("sizeTranslate",properties.getProperty("size"));
        request.setAttribute("typeTranslate",properties.getProperty("type"));
    }
    public void setCategorySettings(HttpServletRequest request, Router router){
        Properties properties = getProperty(router);
        setHeader(request,properties);
        request.setAttribute("titleTranslate",properties.getProperty("settings.category"));
        setSettingsProperties(request,properties);
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
    private void setGuestHeader(HttpServletRequest request, Properties properties) {
        request.setAttribute("signinTranslate",properties.getProperty("header.signin"));
        request.setAttribute("signupTranslate",properties.getProperty("header.signup"));
        request.setAttribute("gadgetsTranslate",properties.getProperty("header.gadgets"));
        request.setAttribute("searchTranslate",properties.getProperty("header.search"));
        request.setAttribute("languageTranslate",properties.getProperty("header.language"));
        request.setAttribute("aboutTranslate",properties.getProperty("header.about"));
    }
    private void setUserHeader(HttpServletRequest request, Properties properties){
        request.setAttribute("gadgetsTranslate",properties.getProperty("headerUser.gadgets"));
        request.setAttribute("searchTranslate",properties.getProperty("headerUser.search"));
        request.setAttribute("profileTranslate",properties.getProperty("headerUser.profile"));
        request.setAttribute("settingsTranslate",properties.getProperty("headerUser.settings"));
        request.setAttribute("signoutTranslate",properties.getProperty("headerUser.signout"));
        request.setAttribute("languageTranslate",properties.getProperty("header.language"));
        request.setAttribute("cartTranslate",properties.getProperty("headerUser.cart"));
        request.setAttribute("aboutTranslate",properties.getProperty("header.about"));
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

    private void setSettingsProperties(HttpServletRequest request, Properties properties){
        request.setAttribute("nextTranslate",properties.getProperty("next"));
        request.setAttribute("prevTranslate",properties.getProperty("prev"));
        request.setAttribute("addTranslate",properties.getProperty("add"));
        request.setAttribute("deleteTranslate",properties.getProperty("delete"));
        request.setAttribute("updateTranslate",properties.getProperty("update"));
        request.setAttribute("idTranslate",properties.getProperty("id"));
        request.setAttribute("nameTranslate",properties.getProperty("name"));
    }
    private void setUserProperties(HttpServletRequest request, Properties properties){
        request.setAttribute("emailTranslate",properties.getProperty("email"));
        request.setAttribute("passwordTranslate",properties.getProperty("password"));
        request.setAttribute("fileTranslate",properties.getProperty("file"));
        request.setAttribute("usernameTranslate",properties.getProperty("username"));
        request.setAttribute("roleTranslate",properties.getProperty("role"));
        request.setAttribute("photoTranslate",properties.getProperty("photo"));
        request.setAttribute("loginTranslate",properties.getProperty("login"));
        request.setAttribute("enterTranslate",properties.getProperty("enter"));
        request.setAttribute("registrTranslate",properties.getProperty("registr"));
        request.setAttribute("addTranslate",properties.getProperty("add"));
        request.setAttribute("submitTranslate",properties.getProperty("submit"));
    }

    private void setGadgetProperties(HttpServletRequest request, Properties properties) {
        request.setAttribute("bdTranslate",properties.getProperty("bd"));
        request.setAttribute("priceTranslate",properties.getProperty("price"));
        request.setAttribute("sdTranslate",properties.getProperty("sd"));
        request.setAttribute("memTranslate",properties.getProperty("mem"));
        request.setAttribute("cpuTranslate",properties.getProperty("cpu"));
        request.setAttribute("audTranslate",properties.getProperty("aud"));
        request.setAttribute("vidTranslate",properties.getProperty("vid"));
        request.setAttribute("batTranslate",properties.getProperty("bat"));
        request.setAttribute("catTranslate",properties.getProperty("cat"));
        request.setAttribute("atcTranslate",properties.getProperty("atc"));
    }
}
