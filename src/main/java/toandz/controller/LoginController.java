package toandz.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginController", urlPatterns = "/login")
public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean accoutCheck = username.equals("admin") && password.equals("123456");
        if (accoutCheck) {
            RequestDispatcher rq = request.getRequestDispatcher("/products");
            request.setAttribute("passStatus","passed");
            rq.forward(request,response);
        } else {
            RequestDispatcher rq = request.getRequestDispatcher("/login.jsp");
            String error = "Tài khoản hoặc mật khẩu không khớp";
            request.setAttribute("error", error);
            rq.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rq = request.getRequestDispatcher("/login.jsp");
        rq.forward(request, response);
    }
}
