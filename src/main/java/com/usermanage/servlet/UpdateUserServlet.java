package com.usermanage.servlet;

import com.usermanage.domain.UserBean;
import com.usermanage.service.UserService;
import com.usermanage.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet(name = "UpdateUserServlet", value = "/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.setCharacterEncoding("utf-8");
            Map<String, String[]> parameterMap = request.getParameterMap();
            System.out.println("-------[for-each循环遍历] 修改后的信息 通过keySet取出map数据-------");
            for(String key:parameterMap.keySet()){
                Object object= parameterMap.get(key);
                String value=((Object[]) object)[0].toString();
                System.out.println("key值："+key+" value值："+value);
            }
             UserBean userBean=new UserBean();
        try {
                BeanUtils.populate(userBean,parameterMap);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            UserService userService=new UserServiceImpl();
            System.out.println( userBean.toString());
            userService.updateUser(userBean);
            response.sendRedirect(request.getContextPath()+"/UserListServlet");

    }
}
