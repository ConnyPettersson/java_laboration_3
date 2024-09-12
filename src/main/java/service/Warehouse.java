package service;
import entities.Product;
import java.util.ArrayList;
import java.util.List;

public class Warehouse {
    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        if(product.name() == null || product.name().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty!");
        }

        for(Product p : products) {
            if(p.id() == product.id()) {
                throw new IllegalArgumentException("Product with the same ID already exists!");
            }
        }
    products.add(product);
    }
}
