package by.ishangulyev.application.controller.command.impl;

import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.command.ActionCommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteGadgetCommand implements ActionCommand {
    @Override public Router execute(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
