package org.example.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest req = (HttpServletRequest) request;

        //放行域
        String[] urls = {"js","/checkCodeServlet", "/loginServlet", "/registerServlet", "/login.jsp","/register.jsp","/selectUserServlet"};

        //请求放行的网址
        String url = req.getRequestURL().toString();
//        System.out.println(url);

        //是否在放行域中
        for (String s : urls) {
            //放行判定
            if (url.contains(s)) {
                //放行
                chain.doFilter(request, response);
                //结束当前执行函数，返回执行能力
                return;
            }
        }

        //获得一个服务器端的会话
        HttpSession session = req.getSession();

        //获得登录信息会话域
        Object loginMsg = session.getAttribute("user");

        //判断会话域是否有登录信息
        if (loginMsg != null) {

            chain.doFilter(request, response);
        } else {
            req.setAttribute("loginMsg","请登录~");
            req.getRequestDispatcher("/login.jsp").forward(req, response);
        }
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }
}
