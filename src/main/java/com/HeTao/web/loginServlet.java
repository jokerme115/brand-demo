package com.HeTao.web;

import com.HeTao.pojo.User;
import com.HeTao.service.BrandService;
import com.HeTao.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
    private final UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取用户名和密码
        String userName = req.getParameter("username");
        String userPassword = req.getParameter("password");
        //调用方法获取User
        User user = userService.SelectUser(userName, userPassword);

        if (user != null)
        {
            //登录成功
            //判断是否选择了记住我
            String remember = req.getParameter("remember");

            //细节最好吧1放前面，因为remember为变量，可能为空会报空指针异常
            if ("1".equals(remember)){
                //发送cookie
                //1.创建cookie
                Cookie cookie_username = new Cookie("username", userName);
                Cookie cookie_userPassword = new Cookie("password", userPassword);

                //设置存活时间
                cookie_username.setMaxAge(60 * 60 * 24 * 15);
                cookie_userPassword.setMaxAge(60 * 60 * 24 * 15);
                //2.发送cookie
                resp.addCookie(cookie_username);
                resp.addCookie(cookie_userPassword);
            }else
            {
                Cookie[] cookies = req.getCookies();
                for (Cookie cookie :cookies)
                    if (cookie.getName().equals("username") || cookie.getName().equals("password"))
                    {
                        cookie.setMaxAge(0);
                        resp.addCookie(cookie);
                    }
            }
            //将登录成功后的user对象存储到session中
            HttpSession session = req.getSession();
            session.setAttribute("user", user);

            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/SelectAllServlet");

        }else {
            //登录失败
            //将登录错误的信息发送到request中
            req.setAttribute("login_msg", "用户名或密码错误");

            //跳转到login.jsp
            req.getRequestDispatcher("/login.jsp").forward(req, resp);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
