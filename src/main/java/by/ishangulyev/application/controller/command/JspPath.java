package by.ishangulyev.application.controller.command;

public enum JspPath {
    INDEX("jsp/main.jsp"),
    CART("jsp/cart.jsp"),
    SIGN_IN("jsp/signin.jsp"),
    SIGN_UP("jsp/signup.jsp"),
    GADGETS("jsp/gadgets.jsp"),
    USERS("jsp/users.jsp"),
    ERROR400("jsp/error4xx.jsp"),
    ERROR00("jsp/error5xx.jsp"),
    ACCOUNT("jsp/account.jsp"),
    SETTINGS("jsp/settings.jsp"),
    BATTERY_SETTINGS("jsp/batterySettings.jsp"),
    CATEGORY_SETTINGS("jsp/categorySettings.jsp"),
    AUDIO_SETTINGS("jsp/audioSettings.jsp"),
    VIDEO_SETTINGS("jsp/videoSettings.jsp"),
    MEMORY_SETTINGS("jsp/memorySettings.jsp"),
    CPU_SETTINGS("jsp/cpuSettings.jsp"),
    GADGET_SETTINGS("jsp/gadgetSettings.jsp"),
    USER_SETTINGS("jsp/userSettings.jsp");
    private String value;

    JspPath(String value){
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
