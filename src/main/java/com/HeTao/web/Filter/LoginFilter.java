package com.HeTao.web.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 拦截资源监测是否进行过登录
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //把servletRequest强转为HttpServletRequest
        HttpServletRequest servlet = (HttpServletRequest) servletRequest;

        //把登录和注册的资源放行
        String[] loginResource = {"/loginServlet", "/css/", "/images/", "login.jsp", "register.jsp", "/RegisterServlet", "/checkCodeServlet"};

        String requestURL = servlet.getRequestURL().toString();

        for (String e : loginResource)
            if (requestURL.contains(e)) {
                //找到了 放行
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        //拦截未登录
        HttpSession session = servlet.getSession();
        Object user = session.getAttribute("user");
        if (user == null)
        {
            //未登录
            servletRequest.setAttribute("login_msg", "请登录！");
            servletRequest.getRequestDispatcher("/login.jsp").forward(servletRequest, servletResponse);
        }else {
            //找到了 放行
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {
    }
}
