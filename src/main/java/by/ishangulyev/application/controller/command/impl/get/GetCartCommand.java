package by.ishangulyev.application.controller.command.impl.get;

import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.command.ActionCommand;
import by.ishangulyev.application.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetCartCommand implements ActionCommand {
    private CartService service = CartService.getInstance();
    @Override public Router execute(HttpServletRequest request, HttpServletResponse response) {
        return service.get(request);
    }
}
