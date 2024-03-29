package com.dera.controller;


import com.dera.dao.AdminDao;
import com.dera.model.Admin;
import com.dera.util.ConnectionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "AdminController", value = "/admin-signup")
public class AdminController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String token = request.getParameter("token");

        Admin newAdmin = new Admin(fullName, email, password, token);
        AdminDao adminDao = new AdminDao(ConnectionUtil.getConnection());
        adminDao.addAdmin(newAdmin);

        response.sendRedirect("admin_dashboard.jsp");
    }
}
