package by.ishangulyev.application.controller.command.impl;

import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.command.ActionCommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SignInCommand implements ActionCommand {
    //private Service service;

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
