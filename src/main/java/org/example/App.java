package org.example;
import entities.Category;
import entities.Product;
import service.Warehouse;
import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();

        Product product1 = new Product(1, "Zildjan", Category.CYMBALS, 7, LocalDate.now(), LocalDate.now());

        warehouse.addProduct(product1);

        System.out.println("Product added successfully");
    }
}
