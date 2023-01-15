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

@WebServlet("/deleteServlet")
public class deleteServlet extends HttpServlet {
    private final BrandService brandService = new BrandService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接受Id
        String id = req.getParameter("id");

        //2.调用service查询
        brandService.delete(Integer.parseInt(id));

        //3.转发到update.jsp
//        req.getRequestDispatcher("/SelectAllServlet").forward(req, resp);
        resp.sendRedirect("/brand-demo/SelectAllServlet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
