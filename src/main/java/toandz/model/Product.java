package toandz.model;

public class Product {
    int code;
    String name = "";
    double price;
    String origin = "Chưa rõ";

    public void setPicture(String picture) {
        this.picture = picture;
    }

    String picture = "img/defaultProduct.jpg";

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Product(String name, double price, String origin) {
        this.code = randomId();
        this.name = name;
        this.price = price;
        this.origin = origin;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Product(String name, double price, String origin, String picture) {
        this.code = randomId();
        this.name = name;
        this.price = price;
        this.origin = origin;
        this.picture = picture;
    }

    public Product(int code, String name, double price, String origin, String picture) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.origin = origin;
        this.picture = picture;
    }

    public Product(int code, String name, double price, String origin) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.origin = origin;
    }

    public String getPicture() {
        return picture;
    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
        this.code = randomId();
    }

    public Product(int code, String name, double price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    private int randomId() {
        return (int) (Math.random() * 10000 + 10000);
    }
}
