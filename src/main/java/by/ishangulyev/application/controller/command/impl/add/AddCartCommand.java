package by.ishangulyev.application.controller.command.impl.add;

import by.ishangulyev.application.controller.AttributeName;
import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.RouterType;
import by.ishangulyev.application.controller.command.ActionCommand;
import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.model.entity.impl.Cart;
import by.ishangulyev.application.service.CartService;
import by.ishangulyev.application.service.GadgetService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AddCartCommand implements ActionCommand {
    private CartService service = CartService.getInstance();
    @Override public Router execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute(AttributeName.CART);
        String id = request.getParameter(AttributeName.ID);
        service.add(cart.getId(),id);
        GadgetService gadgetService = GadgetService.getInstance();
        gadgetService.get(request);
        return new Router(JspPath.GADGETS, RouterType.FORWARD);
    }
}
