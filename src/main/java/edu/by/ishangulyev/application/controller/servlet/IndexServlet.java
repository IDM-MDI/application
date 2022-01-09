package edu.by.ishangulyev.application.controller.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "IndexServlet", value = "/main")
public class IndexServlet extends HttpServlet
{
    private final String JSP_PATH = "jsp/main.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {

    }

    @Override
    public void destroy()
    {

    }

    @Override
    public void init() throws ServletException
    {

    }
}
