package com.HeTao.web;

import com.HeTao.pojo.User;
import com.HeTao.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private final UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("userName");
        String password = req.getParameter("password");
        User user = new User();
        user.setPassword(password);
        user.setUsername(username);

        //调用service注册
        boolean flag = userService.register(user);
        if (flag)
        {
            req.setAttribute("register_msg", "注册成功请登录");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }else
        {
            req.setAttribute("register_msg", "用户名已存在");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
