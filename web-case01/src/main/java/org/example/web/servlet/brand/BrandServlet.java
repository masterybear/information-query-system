package org.example.web.servlet.brand;

import com.alibaba.fastjson.JSON;
import org.example.pojo.Brand;
import org.example.pojo.PageBean;
import org.example.service.BrandService;
import org.example.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet {

    private BrandService brandService = new BrandServiceImpl();

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Brand> brands = brandService.selectAll();

        String jsonString = JSON.toJSONString(brands);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);

    }

    public void addData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();

        Brand brand = JSON.parseObject(params,Brand.class);

        if (brand.getBrandName() != null && !"".equals(brand.getBrandName())) {
            brandService.add(brand);
            response.getWriter().write("success");
        }
    }

    public void updateData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();

        Brand brand = JSON.parseObject(params,Brand.class);

        if (brand.getBrandName() != null && !"".equals(brand.getBrandName())) {
            brandService.update(brand);
            response.getWriter().write("success");
        }
    }

    public void delByBrandId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BufferedReader br = request.getReader();
        String params = br.readLine();

        int[] ids = JSON.parseObject(params,int[].class);

        for (int id : ids) {
            System.out.println(id);
        }

        brandService.delByBrandId(ids);

        response.getWriter().write("success");

    }

    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //（临时变量）现在是第几页——查询起始行
        String _currentPage = request.getParameter("currentPage");
        //这一页需要展示的总行数——需要查询的行数
        String _pageSize = request.getParameter("pageSize");

        //查询条件
        BufferedReader br = request.getReader();
        String params = br.readLine();

        //将前端请求的JSON参数转换为java对象
        Brand brand = JSON.parseObject(params,Brand.class);

        //返回的JSON格式对应的是字符串，要转换为整型才能使用
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        //开始查询
        PageBean<Brand> pageBean = brandService.selectPageAndCondition(currentPage, pageSize, brand);

        //将查询到的数据响应给前端
        String jsonString = JSON.toJSONString(pageBean);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);

    }
}
