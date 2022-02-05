package by.ishangulyev.application.controller;

import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.controller.command.LanguageType;

public class Router {
    private JspPath pagePath;
    private RouterType type;
    private LanguageType language;

    public Router(JspPath pagePath, RouterType routerType,LanguageType language) {
        this.pagePath = pagePath;
        this.type = routerType;
        this.language = language;
    }
    public Router(JspPath pagePath, RouterType routerType) {
        this.pagePath = pagePath;
        this.type = routerType;
        this.language = LanguageType.RU;
    }

    public JspPath getPagePath() {
        return pagePath;
    }

    public void setPagePath(JspPath pagePath) {
        this.pagePath = pagePath;
    }

    public RouterType getRouterType() {
        return type;
    }

    public void setRouterType(RouterType routerType) {
        this.type = routerType;
    }

    public void setLanguage(LanguageType language) {
        this.language = language;
    }
    public void setLanguage(String language) {
        this.language = LanguageType.valueOf(language.toUpperCase());
    }

    public LanguageType getLanguage() {
        return language;
    }
}
