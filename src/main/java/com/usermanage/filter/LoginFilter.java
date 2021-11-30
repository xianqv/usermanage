package com.usermanage.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter",value = "/*")
public class LoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest= (HttpServletRequest) request;
       String uri= httpServletRequest.getRequestURI();
       if(uri.contains("userLogin.jsp")||uri.contains("UserLoginServlet")||uri.contains("/css/")||uri.contains("/js/")||uri.contains("/fonts/")||uri.contains("/checkCodeServlet")){
           chain.doFilter(request, response);
       }else {
           Object user = httpServletRequest.getSession().getAttribute("user");
           if (user != null) {
               chain.doFilter(request, response);
           }else {
               request.setAttribute("login_msg","您尚未登录！");
               request.getRequestDispatcher("/userLogin.jsp").forward(request,response);
           }
       }

    }
}
