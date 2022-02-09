package by.ishangulyev.application.service;

import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.RouterType;
import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.dao.impl.DaoCart;
import by.ishangulyev.application.dao.impl.DaoGadget;
import by.ishangulyev.application.dao.impl.DaoOrder;
import by.ishangulyev.application.dao.impl.DaoUser;
import by.ishangulyev.application.exception.DaoException;
import by.ishangulyev.application.model.entity.impl.Cart;
import by.ishangulyev.application.model.entity.impl.Gadget;
import by.ishangulyev.application.model.entity.impl.Order;
import by.ishangulyev.application.model.entity.impl.User;
import by.ishangulyev.application.util.HashPassGenerator;
import by.ishangulyev.application.validator.UserValidator;
import com.oracle.wls.shaded.org.apache.xpath.operations.Or;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;

import java.util.List;

public class CartService {
    private static CartService instance = new CartService();
    private CartService(){}

    public static CartService getInstance() {
        return instance;
    }

    public boolean add(long cart,String gadget){
        boolean result = true;
        try{
            DaoOrder daoOrder = new DaoOrder();
            Order order = new Order();
            order.setCartID(cart);
            order.setGadgetID(Long.parseLong(gadget));
            daoOrder.create(order);
        }catch (Exception e){
            result = false;
        }
        return result;
    }
    public boolean delete(String gadgetId,long cartId){
        boolean result = true;
        try{
            DaoOrder daoOrder = new DaoOrder();
            long realId = Long.parseLong(gadgetId);
            daoOrder.deleteByGadget(realId,cartId);
        }catch (Exception e){
            result = false;
        }
        return result;
    }

    public Cart findUserCart(User user) {
        Cart cart = null;
        if(!(user == null || user.getEmail() == null || user.getEmail().isEmpty())){
            DaoCart daoCart = new DaoCart();
            try {
                cart = daoCart.findEntityByEmail(user.getEmail());
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }
        else{
            cart = new Cart();
        }
        return cart;
    }
    public List<Order> findOrder(long id){
        DaoOrder daoOrder = new DaoOrder();
        List<Order> orders;
        try {
            orders = daoOrder.findAllByCart(id);
        } catch (DaoException e) {
            orders = null;
        }
        return orders;
    }

    public Router get(HttpServletRequest request) {
        Router router;
        try {
            DaoGadget daoGadget = new DaoGadget();
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("cart");
            List<Order> orders = findOrder(cart.getId());
            List<Gadget> gadgetList = daoGadget.findAllByOrder(orders);
            router = new Router(JspPath.CART,RouterType.FORWARD);
            request.setAttribute("gadgetList",gadgetList);
        } catch (DaoException e) {
            router = new Router(JspPath.ERROR400, RouterType.FORWARD);
        }
        return router;
    }
}
