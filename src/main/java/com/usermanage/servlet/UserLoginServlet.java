package com.usermanage.servlet;

import com.usermanage.domain.UserBean;
import com.usermanage.service.UserService;
import com.usermanage.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserLoginServlet", value = "/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //2.1获取用户填写验证码
        String verifycode = request.getParameter("verifycode");

        //3.验证码校验
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//确保验证码一次性
        if(!checkcode_server.equalsIgnoreCase(verifycode)){
            //验证码不正确
            //提示信息
            request.setAttribute("login_msg","验证码错误！");
            //跳转登录页面
            request.getRequestDispatcher("/userLogin.jsp").forward(request,response);

            return;
        }





        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username+password);
        UserService userService =new UserServiceImpl();
        try{
            UserBean userBean = userService.login(username, password);
            System.out.println(userBean);
            session.setAttribute("user",userBean);
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }catch (Exception e){
            e.printStackTrace();
            request.setAttribute("login_msg","用户名或者密码错误！");
            request.getRequestDispatcher("/userLogin.jsp").forward(request,response);
        }
    }
}
