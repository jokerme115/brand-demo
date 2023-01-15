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

@WebServlet("/addServlet")
public class addServlet extends HttpServlet {
    private final BrandService brandService = new BrandService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //处理乱码
        req.setCharacterEncoding("UTF-8");

        //1.接受表单提交的数据,封装
        String brandName = req.getParameter("brandName");
        String companyName = req.getParameter("companyName");
        String ordered = req.getParameter("ordered");
        String description = req.getParameter("description");
        String status = req.getParameter("status");

        //2.封装
        Brand brand = new Brand(brandName, companyName, Integer.parseInt(ordered), description, Integer.parseInt(status));

        //3.调用service完成添加
        brandService.add(brand);

        //3.转发到查询所有brand.jsp
        req.getRequestDispatcher("/SelectAllServlet").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
