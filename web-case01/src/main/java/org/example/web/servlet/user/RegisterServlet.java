package org.example.web.servlet.user;

import org.example.pojo.VirtualUser;
import org.example.service.VUserService;
import org.example.service.impl.VUserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {

    private VUserService service = new VUserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取用户名、密码和验证码，并去除字符串中的空白
        String username = request.getParameter("username").replaceAll("\\s+","");
        String password = request.getParameter("password").replaceAll("\\s+","");
        String checkCode = request.getParameter("checkCode");

        VirtualUser user = new VirtualUser();
        user.setUserName(username);
        user.setPassword(password);

        //获取生成的验证码
        HttpSession session = request.getSession();
        String checkCodeGen = (String) session.getAttribute("checkCodeGen");

        String key = "registerMsg";
        String value = null;
        String path = "/register.jsp";

        /**
         * @param key:作为键传入setAttribute()方法
         * @param notes:作为值传入setAttribute()方法
         * @param path:需要跳转的地址名称
         */
        if ("".equals(username) || "".equals(password)) {
            value = "用户名或密码不能为空！";

        } else {
            if (!checkCodeGen.equalsIgnoreCase(checkCode)) {
                value = "验证码输入错误！";

            } else {

                //是否成功注册
                boolean b = service.register(user);

                if (b) {
                    key = "loginMsg";
                    value = "注册成功，请登录";
                    path = "/login.jsp";
                } else {
                    value = "用户名已存在";
                }
            }
        }

        //跳转到对应页面
        request.setAttribute(key,value);
        request.getRequestDispatcher(path).forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
