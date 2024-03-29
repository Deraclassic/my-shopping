package com.dera.servlet;

import com.dera.dao.AdminDao;
import com.dera.model.Admin;
import com.dera.util.ConnectionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
@WebServlet(name = "AdminLoginServlet", value = "/admin_login")
public class AdminLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("admin_login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String email = request.getParameter("email"); // Corrected parameter name
            String password = request.getParameter("password"); // Corrected parameter name
            String token = request.getParameter("token"); // Corrected parameter name

            AdminDao adminDao = new AdminDao(ConnectionUtil.getConnection());
            Admin admin = adminDao.adminLogin(email, password, token);

            if (admin != null) {
                request.getSession().setAttribute("auth", admin);
                response.sendRedirect("admin_dashboard.jsp");
            } else {
                out.println("Admin login failed");
            }
            // Remove this line if you don't want to print the email and password to the response
            out.println(email + password);
        }
    }
}
