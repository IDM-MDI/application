package by.ishangulyev.application.controller;

import by.ishangulyev.application.controller.command.CommandType;
import by.ishangulyev.application.controller.command.RequestParameter;
import by.ishangulyev.application.controller.command.ActionCommand;
import by.ishangulyev.application.validator.ParameterValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;


@WebServlet(name = "IndexServlet", urlPatterns = {"/controller"})
public class IndexServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    private final ParameterValidator validator = new ParameterValidator();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(validator.isParameterValid(req)){
            String commandName = req.getParameterNames().nextElement().toUpperCase();

            CommandType commandType = commandName.equals(RequestParameter.COMMAND.name())?
                    CommandType.valueOf(req.getParameter(commandName)):
                    CommandType.valueOf(commandName.toUpperCase());

            ActionCommand command = commandType.getCommand();
            Router router = command.execute(req,resp);
            RouterType routingType = router.getRouterType();
            String resultPage = router.getPagePath();
            switch (routingType) {
                case FORWARD: {
                    req.getRequestDispatcher(resultPage).forward(req, resp);
                }
                break;
                case REDIRECT: {
                    resp.sendRedirect(req.getContextPath() + resultPage);
                }
                break;
                default: {
                    logger.log(Level.ERROR, "Invalid router type");
                }
            }
        }
    }

}

