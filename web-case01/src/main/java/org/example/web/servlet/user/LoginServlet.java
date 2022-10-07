package org.example.web.servlet.user;

import org.example.pojo.VirtualUser;
import org.example.service.VUserService;
import org.example.service.impl.VUserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    private VUserService service = new VUserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");

        VirtualUser user = service.login(username, password);
        //登录成功
        if (user != null) {
            //记住密码的逻辑
            if ("1".equals(remember)) {

                Cookie c_username = new Cookie("username", username);
                Cookie c_password = new Cookie("password", password);
                // 3 days
                c_username.setMaxAge(259200);
                c_password.setMaxAge(259200);
                response.addCookie(c_username);
                response.addCookie(c_password);
            }

            //获取服务器端会话
            HttpSession session = request.getSession();
            session.setAttribute("user",user);

            //重定向到内容界面
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/brand.html");

        }else {

            //登录信息的提示赋值
            String value = null;
            //判断登录信息是否正确
            if (username != null) {
                value = "用户名或密码错误";
            }
            //跳转回来，重新输入密码
            request.setAttribute("loginMsg", value);
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
