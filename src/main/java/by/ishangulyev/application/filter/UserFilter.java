package by.ishangulyev.application.filter;

import by.ishangulyev.application.service.SessionService;
import by.ishangulyev.application.validator.SessionValidator;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/controller"},servletNames = {"IndexServlet"})
public class UserFilter implements Filter {
    private final SessionService sessionService = SessionService.getInstance();
    private final SessionValidator validator = SessionValidator.getInstance();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = sessionService.sessionHandler(request,response);
//        if(validator.isAdminPage(request) && !validator.isAdmin(session)){
//            return;
//        }
//        else if(validator.isUserPage(request) && !validator.isUser(session)){
//            return;
//        }
//        if(!validator.isAdmin(session)){
//            return;
//        }
//        else if(!validator.isUser(session)){
//            return;
//        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
