package com.HeTao.web;

import com.HeTao.pojo.User;
import com.HeTao.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private final UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String checkCode = req.getParameter("checkCode");

        User user = new User();
        user.setPassword(password);
        user.setUsername(username);

        //获取程序生成的验证码
        HttpSession session = req.getSession();
        String checkCode1 = (String)session.getAttribute("checkCode");

        if (!checkCode1.equalsIgnoreCase(checkCode))
        {
            //验证错误
            req.setAttribute("checkCodeMsg", "验证码错误,请重试");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }

        //调用service注册
        int flag = userService.register(user);
        switch (flag)
        {
            case 1:
            req.setAttribute("login_msg", "注册成功");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            break;

            case 2:
            req.setAttribute("register_msg", "用户名已存在");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            break;

            case 3:
                req.setAttribute("register_msg", "用户名不能为空");
                req.getRequestDispatcher("/register.jsp").forward(req, resp);
            break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
