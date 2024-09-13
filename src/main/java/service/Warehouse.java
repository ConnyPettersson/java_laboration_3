package service;
import entities.Category;
import entities.Product;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Product> getAllProducts() {
        for(Product product : products) {
            System.out.println(product);
        }
        return Collections.unmodifiableList(products);
    }

    public List<Product> getProductSortedByName() {
        return products.stream()
                .sorted(Comparator.comparing(Product::name))
                .collect(Collectors.toList());
    }

    public List<Product> getProductsByCategory(Category category) {
        return products.stream()
                .filter(product -> product.category().equals(category))
                .collect(Collectors.toList());
    }

    public Product getProductById(int id) {
        return products.stream()
                .filter(product -> product.id() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Product with ID " + id + " not found!"));
    }
}
