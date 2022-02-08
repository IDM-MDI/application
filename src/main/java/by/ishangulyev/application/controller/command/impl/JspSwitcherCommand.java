package by.ishangulyev.application.controller.command.impl;

import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.RouterType;
import by.ishangulyev.application.controller.command.ActionCommand;
import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.controller.command.RequestParameterName;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JspSwitcherCommand implements ActionCommand {
    @Override public Router execute(HttpServletRequest request, HttpServletResponse response) {
        JspPath path;
        switch (request.getParameter(RequestParameterName.JSP_SWITCHER.name().toLowerCase())){
            case "index" -> path = JspPath.INDEX;
            case "gadgets" -> path = JspPath.GADGETS;
            case "users" -> path = JspPath.USERS;
            case "signin" -> path = JspPath.SIGN_IN;
            case "signup" -> path = JspPath.SIGN_UP;
            case "account" -> path = JspPath.ACCOUNT;
            case "cart" -> path = JspPath.CART;
            case "addGadget" -> path = JspPath.ADD_GADGET;
            case "settings" -> path = JspPath.SETTINGS;
            case "batterysettings" -> path = JspPath.BATTERY_SETTINGS;
            case "usersettings" -> path = JspPath.USER_SETTINGS;
            default -> path = JspPath.ERROR400;
        }
        request.getSession().setAttribute("page",path);
        return new Router(path, RouterType.FORWARD);
    }
}
