package com.usermanage.servlet;

import com.usermanage.service.UserService;
import com.usermanage.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "DelSelectedUsersServlet", value = "/DelSelectedUsersServlet")
public class DelSelectedUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String[] ids=request.getParameterValues("uid");
        for (int i = 0; i < ids.length; i++) {
            System.out.println("seleced ids ："+ids[i]);
        }
        UserService userService =new UserServiceImpl();
        userService.delSelectedUser(ids);
        response.sendRedirect(request.getContextPath()+"/UserListServlet");
    }
}
