package by.ishangulyev.application.controller.command;

import by.ishangulyev.application.controller.Router;
import jakarta.servlet.http.HttpServletRequest;

public interface ActionCommand {
    Router execute(HttpServletRequest req);
}
