package service;

import entities.Category;
import entities.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseTest {

    private Warehouse warehouse;

    @BeforeEach
    void setUp() {
        warehouse = new Warehouse();
    }
    @Test
    @DisplayName("Add a product successfully to the warehouse")
    void addProduct_success() {
        Product product = new Product(1, "Zildjan", Category.CYMBALS, 7, LocalDate.now(), LocalDate.now());
        warehouse.addProduct(product);

        List<Product> allProducts = warehouse.getProductSortedByName();
        assertEquals(1, allProducts.size(), "Expected 1 product in the warehouse");
    }
}