package by.ishangulyev.application.controller.command.impl.delete;

import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.RouterType;
import by.ishangulyev.application.controller.command.ActionCommand;
import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.model.entity.impl.Cart;
import by.ishangulyev.application.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DeleteCartCommand implements ActionCommand {
    private CartService service = CartService.getInstance();
    @Override public Router execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        String id = request.getParameter("id");
        service.delete(id,cart.getId());
        service.get(request);
        return new Router(JspPath.CART, RouterType.FORWARD);
    }
}
