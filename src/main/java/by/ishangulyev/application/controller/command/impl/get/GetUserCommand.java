package by.ishangulyev.application.controller.command.impl.get;

import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.RouterType;
import by.ishangulyev.application.controller.command.ActionCommand;
import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.dao.impl.DaoBattery;
import by.ishangulyev.application.dao.impl.DaoUser;
import by.ishangulyev.application.exception.DataBaseException;
import by.ishangulyev.application.model.entity.impl.Battery;
import by.ishangulyev.application.model.entity.impl.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class GetUserCommand implements ActionCommand {
    @Override public Router execute(HttpServletRequest request, HttpServletResponse response) {
        String page = request.getParameter("page");

        if(page == null || page.isEmpty()){
            page = "1";
        }

        int pageNumber = Integer.parseInt(page);
        int next = 0,prev = pageNumber-1;
        List<User> userList = null;
        DaoUser daoUser = new DaoUser();

        try {
            pageNumber--;
            userList = daoUser.findByCount(pageNumber);
            if(userList.size() > 9){
                next = pageNumber+1;
                userList.remove(userList.size()-1);
            }

        } catch (DataBaseException e) {
            e.printStackTrace();
        }

        request.setAttribute("userList",userList);
        request.setAttribute("currentPage",++pageNumber);
        request.setAttribute("nextPage",next);
        request.setAttribute("prevPage",prev);

        return new Router(JspPath.USERSETTINGS, RouterType.FORWARD);
    }
}
