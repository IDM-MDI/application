package by.ishangulyev.application.controller.command.impl;

import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.RouterType;
import by.ishangulyev.application.controller.command.ActionCommand;
import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.controller.command.RequestParameterName;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JspSwitcherCommand implements ActionCommand {
    private JspPath path;
    @Override public Router execute(HttpServletRequest request, HttpServletResponse response) {
        switch (request.getParameter(RequestParameterName.JSP_SWITCHER.name().toLowerCase())){
            case "index" -> path = JspPath.INDEX;
            case "gadgets" -> path = JspPath.GADGETS;
            case "users" -> path = JspPath.USERS;
            case "signin" -> path = JspPath.SIGN_IN;
            case "signup" -> path = JspPath.SIGN_UP;
            case "account" -> path = JspPath.ACCOUNT;
            default -> path = JspPath.ERROR4XX;
        }
        return new Router(path, RouterType.FORWARD);
    }
}
