package by.ishangulyev.application.controller.command.impl.get;

import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.command.ActionCommand;
import by.ishangulyev.application.dao.impl.DaoGadget;
import by.ishangulyev.application.exception.DataBaseException;
import by.ishangulyev.application.model.entity.impl.Gadget;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class GetGadgetCommand implements ActionCommand {
    @Override public Router execute(HttpServletRequest request, HttpServletResponse response) {
        String page = request.getParameter("page");
        int pageNumber = Integer.parseInt(page);
        List<Gadget> gadgetList = null;
        DaoGadget daoGadget = new DaoGadget();
        try {
            gadgetList = daoGadget.findByCount(--pageNumber);
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
        request.setAttribute("gadgetList",gadgetList);
        return null;
    }
}
