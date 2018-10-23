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
    private boolean isPassed = false;

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
                    editHandle(request, response);
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
                case "view":
                    getViewPage(request, response);
                    break;
                case "delete":
                    deleteHandle(request, response);
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
        request.setAttribute("product", product);
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

    private void getViewPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rq = request.getRequestDispatcher("/products/view.jsp");
        int code = Integer.parseInt(request.getParameter("code"));
        Product product = products.find(code);
        request.setAttribute("product", product);
        rq.forward(request, response);
    }

    private void addHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rq = request.getRequestDispatcher("/products/add.jsp");
        String error = "";


        boolean notInform = request.getParameter("name").equals("") ||
                request.getParameter("price").equals("") ||
                request.getParameter("origin").equals("");
        if (!notInform) {
            String linkpic = "img/defaultProduct.jpg";
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
                RequestDispatcher dispatcher = request.getRequestDispatcher("/products/workspace.jsp");
                request.setAttribute("log", "Thêm thành công !");
                request.setAttribute("products", products);
                dispatcher.forward(request, response);
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

    private void deleteHandle(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int code = Integer.parseInt(request.getParameter("code"));
        if (products.delete(code)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/products/workspace.jsp");
            request.setAttribute("log", "Xóa thành công !");
            request.setAttribute("products", products);
            dispatcher.forward(request, response);

        } else {
            response.sendRedirect("/error404.jsp");
        }
    }

    private void editHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rq = request.getRequestDispatcher("/products/edit.jsp");
        String error = "";
        boolean isDone = false;
        int code = Integer.parseInt(request.getParameter("code"));
        Product product = products.find(code);
        request.setAttribute("product", product);

        if ((!(request.getParameter("picture").equals(""))) &&
                request.getParameter("picture") != null) {
            product.setPicture(request.getParameter("picture"));
            isDone = true;
        }
        if ((!(request.getParameter("name").equals(""))) &&
                request.getParameter("name") != null) {
            product.setName(request.getParameter("name"));
            isDone = true;
        }
        if ((!(request.getParameter("origin").equals(""))) &&
                request.getParameter("origin") != null) {
            product.setOrigin(request.getParameter("origin"));
            isDone = true;
        }
        if ((!(request.getParameter("price").equals(""))) &&
                request.getParameter("price") != null) {
            try {
                Double.parseDouble(request.getParameter("price"));
            } catch (Throwable e) {
                error = "Giá không hợp lệ";
                request.setAttribute("error", error);
                rq.forward(request, response);
                isDone = false;
            }
            double price = Double.parseDouble(request.getParameter("price"));
            product.setPrice(price);
            isDone = true;
        }
        if (isDone) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/products/view.jsp");
            String log = "update thành công";
            request.setAttribute("log", log);
            dispatcher.forward(request, response);
        } else {
            error = "xin hãy điền gì đó!";
            request.setAttribute("error", error);
            rq.forward(request, response);
        }
    }

}
