package by.ishangulyev.application.controller.command.impl.get;

import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.RouterType;
import by.ishangulyev.application.controller.command.ActionCommand;
import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.dao.impl.DaoGadget;
import by.ishangulyev.application.exception.DaoException;
import by.ishangulyev.application.exception.DataBaseException;
import by.ishangulyev.application.model.entity.impl.Gadget;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class GetGadgetCommand implements ActionCommand {
    @Override public Router execute(HttpServletRequest request, HttpServletResponse response) {
        String page = request.getParameter("page");
        if(page == null || page.isEmpty()){
            page = "1";
        }
        int pageNumber = Integer.parseInt(page);
        int next = 0,prev = pageNumber-1;
        List<Gadget> gadgetList = null;
        DaoGadget daoGadget = new DaoGadget();

        try {
            pageNumber--;
            gadgetList = daoGadget.findByCount(pageNumber);
            if(gadgetList.size() > 9){
                next = pageNumber+1;
                gadgetList.remove(gadgetList.size()-1);
            }

        } catch (DaoException e) {
            e.printStackTrace();
        }

        request.setAttribute("gadgetList",gadgetList);
        request.setAttribute("currentPage",++pageNumber);
        request.setAttribute("nextPage",next);
        request.setAttribute("prevPage",prev);

        return new Router(JspPath.GADGETS, RouterType.FORWARD);
    }
}
