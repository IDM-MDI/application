package by.ishangulyev.application.controller;

import by.ishangulyev.application.controller.command.ActionCommand;
import by.ishangulyev.application.service.LanguageService;
import by.ishangulyev.application.service.RequestService;
import by.ishangulyev.application.service.SessionService;
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
    private final SessionService sessionService = new SessionService();
    private final RequestService requestService = new RequestService();
    private final LanguageService languageService = new LanguageService();
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
            sessionService.sessionHandler(req,resp);
            ActionCommand command = requestService.getCommand(req);
            Router router = command.execute(req,resp);
            router.setLanguage(sessionService.getType());
            languageService.setLanguageAtPage(req,router);
            switch (router.getRouterType()) {
                case FORWARD: {
                    req.getRequestDispatcher(router.getPagePath().getValue()).forward(req, resp);
                }
                break;
                case REDIRECT: {
                    resp.sendRedirect(req.getContextPath() + router.getPagePath().getValue());
                }
                break;
                default: {
                    logger.log(Level.ERROR, "Invalid router type");
                }
            }
        }
    }


}

