package by.ishangulyev.application.controller.command;

public enum JspPath {
    INDEX("src/main/webapp/jsp/main.jsp"),
    SIGN_IN("src/main/webapp/jsp/signin.jsp"),
    SIGN_UP("src/main/webapp/jsp/signup.jsp"),
    GADGETS("src/main/webapp/jsp/gadgets.jsp"),
    USERS("src/main/webapp/jsp/users.jsp"),
    ERROR4XX("src/main/webapp/jsp/error4xx.jsp"),
    ERROR5XX("src/main/webapp/jsp/error5xx.jsp");

    private String value;

    JspPath(String value){
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
