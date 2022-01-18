package edu.by.ishangulyev.application.controller;

public class Router
{
    private String pagePath;
    private RouterType type;

    public Router(String pagePath, RouterType routerType)
    {
        this.pagePath = pagePath;
        this.type = routerType;
    }

    public String getPagePath()
    {
        return pagePath;
    }

    public void setPagePath(String pagePath)
    {
        this.pagePath = pagePath;
    }

    public RouterType getRouterType()
    {
        return type;
    }

    public void setRouterType(RouterType routerType)
    {
        this.type = routerType;
    }
}
