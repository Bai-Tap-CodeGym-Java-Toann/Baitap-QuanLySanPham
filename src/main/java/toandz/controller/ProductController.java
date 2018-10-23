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
    private ProductsServ products = new ProductsServ();
    private boolean isPassed = true;

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
                case "add":
                    addHandle(request, response);
                    break;
                case "edit":
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
                case "add":
                    getAddPage(request, response);
                    break;
                case "edit":
                    getEditPage(request, response);
                    break;
                case "search":
                    response.sendRedirect("/error404.jsp");
                    break;
                case "delete":
                    deleteHandle(request,response);
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
        request.setAttribute("product",product);
        if (product != null) {
            rq.forward(request, response);
        } else {
            response.sendRedirect("/error404.jsp");
        }
    }

    private void getAddPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rq = request.getRequestDispatcher("/products/add.jsp");
        rq.forward(request, response);
    }

    private void addHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rq = request.getRequestDispatcher("/products/add.jsp");
        String error = "";


        boolean notInform = request.getParameter("name").equals("")||
                request.getParameter("price").equals("") ||
                request.getParameter("origin").equals("");
        if (!notInform) {
            String linkpic = "";
            if (request.getParameter("picture") != null) {
                linkpic = request.getParameter("picture");
            }
            try {
                Double.parseDouble(request.getParameter("price"));
            } catch (Throwable e) {
                error = "Giá không hợp lệ";
                request.setAttribute("error", error);
                rq.forward(request, response);
            }

            String name = request.getParameter("name");
            double price = Double.parseDouble(request.getParameter("price"));
            String origin = request.getParameter("origin");

            Product newProduct;
            if (request.getParameter("code") != null) {
                if (request.getParameter("code").matches("^[0-9]+$")) {
                    int code = Integer.parseInt(request.getParameter("code"));
                    newProduct = new Product(code, name, price, origin, linkpic);
                } else {
                    newProduct = new Product(name, price, origin, linkpic);
                }
            } else {
                newProduct = new Product(name, price, origin, linkpic);
            }
            if (products.add(newProduct)) {
                response.sendRedirect("/products");
            } else {
                error = "code đã tồn tại";
                request.setAttribute("error", error);
                rq.forward(request, response);
            }
        } else {
            error = "cần điền đủ các trường (*)";
            request.setAttribute("error", error);
            rq.forward(request, response);
        }
    }
    private void deleteHandle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int code = Integer.parseInt(request.getParameter("code"));
        if (products.delete(code)){
            response.sendRedirect("/products");
        }else {
            response.sendRedirect("/error404.jsp");
        }
    }
}
