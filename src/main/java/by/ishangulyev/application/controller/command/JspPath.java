package by.ishangulyev.application.controller.command;

public enum JspPath {
    INDEX("jsp/main.jsp"),
    CART("jsp/cart.jsp"),
    SIGN_IN("jsp/signin.jsp"),
    SIGN_UP("jsp/signup.jsp"),
    GADGETS("jsp/gadgets.jsp"),
    USERS("jsp/users.jsp"),
    ERROR4XX("jsp/error4xx.jsp"),
    ERROR5XX("jsp/error5xx.jsp"),
    ACCOUNT("jsp/account.jsp"),
    ADDGADGET("jsp/addGadget.jsp"),
    UPDATEGADGET("jsp/updateGadget.jsp"),
    UPDATEUSER("jsp/updateUser.jsp");

    private String value;

    JspPath(String value){
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
