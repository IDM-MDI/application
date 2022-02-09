package by.ishangulyev.application.controller.command.impl.delete;

import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.RouterType;
import by.ishangulyev.application.controller.command.ActionCommand;
import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.model.entity.impl.User;
import by.ishangulyev.application.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class DeleteUserCommand implements ActionCommand {
    private UserService service = UserService.getInstance();
    @Override public Router execute(HttpServletRequest request, HttpServletResponse response) {
        String delete = request.getParameter("email");
        service.deleteAccount(delete);
        return service.getAccounts(request);
    }
}
