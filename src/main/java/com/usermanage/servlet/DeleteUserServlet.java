package com.usermanage.servlet;

import com.usermanage.service.UserService;
import com.usermanage.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteUserServlet", value = "/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uid = request.getParameter("uid");
        UserService userService=new UserServiceImpl();
        userService.deleteUser(uid);
        response.sendRedirect(request.getContextPath()+"/UserListServlet");
    }
}
