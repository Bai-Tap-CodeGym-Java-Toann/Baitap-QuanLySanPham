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
    boolean isPassed = false;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getAttribute("passStatus").equals("passed")) {
            isPassed = true;
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
}
