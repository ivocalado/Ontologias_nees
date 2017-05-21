package com.abuarquemf.servlets;

import com.abuarquemf.persistence.DataBaseHandler;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class AvatarList
 */
@WebServlet("/avatars/list")
public class AvatarList extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AvatarList() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        DataBaseHandler persistence = DataBaseHandler.getInstance();
        Gson g = new Gson();
        response.getWriter().println(
                g.toJson(persistence.getAvatars()).toLowerCase());
    }

}
