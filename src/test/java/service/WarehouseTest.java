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
    void addProduct_withSuccess() {
        Product product = new Product(1, "Zildjan", Category.CYMBALS, 7, LocalDate.now(), LocalDate.now());
        warehouse.addProduct(product);

        List<Product> allProducts = warehouse.getProductSortedByName();
        assertEquals(1, allProducts.size(), "Expected 1 product in the warehouse");
    }

    @Test
    @DisplayName("Throw exception when adding a product with a duplicate ID")
    void addProduct_thatHasDuplicateId_throwException() {
        Product product1 = new Product(1, "Zildjan", Category.CYMBALS, 7, LocalDate.now(), LocalDate.now());
        Product product2 = new Product(1, "Meinl", Category.CYMBALS, 4, LocalDate.now(), LocalDate.now());
        warehouse.addProduct(product1);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            warehouse.addProduct(product2);
        });
        assertTrue(exception.getMessage().contains("Product with the same ID already exists"));
    }

    @Test
    @DisplayName("Successfully update an existing product in the warehouse")
    void updateProduct_withSuccess() {
        Product product = new Product(1, "Zildjian", Category.CYMBALS, 7, LocalDate.now(), LocalDate.now());
        warehouse.addProduct(product);

        warehouse.updateProduct(1, "Zildjan K Series", Category.CYMBALS, 9);

        Product updatedProduct = warehouse.getProductById(1);
        assertEquals("Zildjan K Series", updatedProduct.name(), "Expected the product name to be updated");
        assertEquals(9, updatedProduct.rating(), "Expect the product rating to be updated");
    }


}