package edu.by.ishangulyev.application;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet
{
    private String message;
    private final String JSP_PATH = "jsp/signin.jsp";
    public void init()
    {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
//        response.setContentType("text/jsp");
//        String one = request.getParameter("one");
//        request.setAttribute("one",one);
//        request.getRequestDispatcher(JSP_PATH).forward(request,response);
    }

    public void destroy()
    {
    }
}