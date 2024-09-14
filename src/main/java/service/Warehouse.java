package service;
import entities.Category;
import entities.Product;

import java.time.LocalDate;
import java.util.ArrayList;
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

    public void getAllProducts() {
        for(Product product : products) {
            System.out.println(product);
        }
    }

    public List<Product> getProductSortedByName() {
        return products.stream()
                .sorted(Comparator.comparing(Product::name))
                .collect(Collectors.toList());
    }

    public List<Product> getProductsByCategory(Category category) {
        return products.stream()
                .filter(product -> product.category().equals(category))
                .sorted(Comparator.comparing(Product::name))
                .collect(Collectors.toList());
    }

    public Product getProductById(int id) {
        return products.stream()
                .filter(product -> product.id() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Product with ID " + id + " not found!"));
    }

    public List<Product> getProductsCreatedOn(LocalDate date) {
        return products.stream()
                .filter(product -> product.createdDate().equals(date))
                .collect(Collectors.toList());
    }

    public List<Product> getModifiedProduct() {
        return products.stream()
                .filter(product -> !product.createdDate().equals(product.modifiedDate()))
                .collect(Collectors.toList());
    }
}
