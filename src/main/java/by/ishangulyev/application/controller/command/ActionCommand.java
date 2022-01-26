package by.ishangulyev.application.controller.command;

import by.ishangulyev.application.controller.Router;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ActionCommand {
    Router execute(HttpServletRequest request, HttpServletResponse response);
}
