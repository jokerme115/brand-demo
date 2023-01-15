package com.HeTao.web;

import com.HeTao.pojo.Brand;
import com.HeTao.service.BrandService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/SelectAllServlet")
public class SelectAllServlet extends HttpServlet {
    private final BrandService brandService = new BrandService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.调用BrandService完成查询
        List<Brand> brands = brandService.selectAll();

        //2.存入req域中
        req.setAttribute("brands", brands);

        //3.转发到brand.jsp
        req.getRequestDispatcher("/brand.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
