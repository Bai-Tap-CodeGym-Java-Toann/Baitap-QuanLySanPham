package toandz.service;

import toandz.model.Product;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductsServ implements ProductsService {
    HashMap<Integer, Product> products;

    public ProductsServ() {
        products = new HashMap<>();
        this.add(new Product("bánh bao", 6000, "hà nội"));
        this.add(new Product("bánh mì 2 trứng", 12000,"hà nội"));
        this.add(new Product("bánh mì 2 trứng pate", 15000, "hải phòng"));
        this.add(new Product("bánh mì trứng pate", 10000,"hồ chí minh"));
        this.add(new Product("chè thái gái tuyên",10000,"thái nguyên"));
    }

    @Override
    public ArrayList<Product> list() {
        return new ArrayList<Product>(products.values());
    }

    @Override
    public boolean add(Product product) {
        if (products.containsKey(product.getCode())) {
            return false;
        }
        products.put(product.getCode(), product);
        return true;
    }

    @Override
    public boolean delete(int code) {
        try {
            products.remove(code);
            return true;
        } catch (Throwable a) {
            return false;
        }
    }

    @Override
    public boolean update(int code, Product product) {
        if (products.containsKey(product.getCode())) {
            products.put(product.getCode(), product);
            return true;
        }
        return false;
    }

    @Override
    public Product find(int code) {
        if (products.containsKey(code)) {
            return products.get(code);
        }
        return null;
    }
}
