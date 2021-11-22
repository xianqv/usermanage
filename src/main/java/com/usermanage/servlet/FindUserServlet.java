package com.usermanage.servlet;

import com.usermanage.domain.UserBean;
import com.usermanage.service.UserService;
import com.usermanage.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "FindUserServlet", value = "/FindUserServlet")
public class FindUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService =new UserServiceImpl();
        String uid =request.getParameter("uid");
        UserBean user = userService.findUserById(uid);
        request.setAttribute("user",user);
        request.getRequestDispatcher("/updateUser.jsp").forward(request,response);
    }
}
