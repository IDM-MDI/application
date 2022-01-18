package edu.by.ishangulyev.application.controller;

import edu.by.ishangulyev.application.controller.command.ActionCommand;
import edu.by.ishangulyev.application.controller.command.CommandType;
import edu.by.ishangulyev.application.controller.command.RequestParameter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;


@WebServlet(name = "IndexServlet", urlPatterns = {"/main"})
public class IndexServlet extends HttpServlet
{
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        processRequest(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        processRequest(req,resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String commandName = req.getParameter(RequestParameter.COMMAND.toString());
        CommandType commandType = CommandType.valueOf(commandName.toUpperCase(Locale.ROOT));
        ActionCommand command = commandType.getCommand();
        Router router = command.execute(req);
        RouterType routingType = router.getRouterType();
        String resultPage = router.getPagePath();
        switch (routingType) {
            case FORWARD:
            {
                req.getRequestDispatcher(resultPage).forward(req, resp);
            }
            break;
            case REDIRECT:
            {
                resp.sendRedirect(req.getContextPath() + resultPage);
            }
            break;
            default: {
                logger.log(Level.ERROR, "Invalid router type");
            }
        }
    }

}

