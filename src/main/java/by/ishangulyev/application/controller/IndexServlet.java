package by.ishangulyev.application.controller;

import by.ishangulyev.application.controller.command.ActionCommand;
import by.ishangulyev.application.controller.command.LanguageType;
import by.ishangulyev.application.service.LanguageService;
import by.ishangulyev.application.validator.ParameterValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;


@WebServlet(name = "IndexServlet", urlPatterns = {"/controller"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 15,
                 maxFileSize = 1024 * 1024 * 15,
                 maxRequestSize = 1024 * 1024 * 15)
public class IndexServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    private final ParameterValidator validator = ParameterValidator.getInstance();
    private final CommandFactory commandFactory = new CommandFactory();
    private final LanguageService languageService = LanguageService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override public void destroy() {}

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(validator.isParameterValid(req)){
            ActionCommand command = commandFactory.getCommand(req);
            Router router = command.execute(req,resp);
            router.setLanguage((LanguageType) req.getSession().getAttribute("language"));
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

