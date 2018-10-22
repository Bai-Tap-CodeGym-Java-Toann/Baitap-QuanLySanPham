package toandz.service;

import toandz.model.Product;

import java.util.ArrayList;

public interface ProductsService {
    public ArrayList<Product> list();

    public boolean delete(int code);

    public boolean add(Product product);

    public boolean update(int code, Product product);

    public Product find(int code);
}
