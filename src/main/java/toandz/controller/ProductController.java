package toandz.controller;

import toandz.model.Product;
import toandz.service.ProductsServ;
import toandz.service.ProductsService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "ProductController", urlPatterns = "/products")
public class ProductController extends HttpServlet {
    ProductsServ products = new ProductsServ();
    boolean isPassed = true;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getAttribute("passStatus") != null) {
            if (request.getAttribute("passStatus").equals("passed")) {
                isPassed = true;
            }
        }
        if (isPassed) {
            String action = request.getParameter("action");
            if (action == null) {
                action = "";
            }
            switch (action) {
                case "o":
                    break;
                default:
                    getWorkspace(request, response);
                    break;
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isPassed) {
            String action = request.getParameter("action");
            if (action == null) {
                action = "";
            }
            switch (action) {
                case "edit":
                    getEditPage(request, response);
                    break;
                case "search":
                    response.sendRedirect("/error404.jsp");
                    break;
                default:
                    getWorkspace(request, response);
                    break;
            }
        }
    }


    private void getWorkspace(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        ArrayList<Product> productList = products.list();
        request.setAttribute("products", productList);
        RequestDispatcher rq = request.getRequestDispatcher("/products/workspace.jsp");
        rq.forward(request, response);
    }

    private void getEditPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rq = request.getRequestDispatcher("/products/edit.jsp");
        int code = Integer.parseInt(request.getParameter("code"));
        Product product = products.find(code);
        if (product != null) {
            rq.forward(request, response);
        } else {
            response.sendRedirect("/error404.jsp");
        }
    }
}
