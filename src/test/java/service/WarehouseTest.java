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
        Product product = new Product(1, "Zildjian", Category.CYMBALS, 7, LocalDate.now(), LocalDate.now());
        warehouse.addProduct(product);

        List<Product> allProducts = warehouse.getProductSortedByName();
        assertEquals(1, allProducts.size(), "Expected 1 product in the warehouse");
    }

    @Test
    @DisplayName("Throw exception when adding a product with a duplicate ID")
    void addProduct_thatHasDuplicateId_throwException() {
        Product product1 = new Product(1, "Zildjian", Category.CYMBALS, 7, LocalDate.now(), LocalDate.now());
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

        warehouse.updateProduct(1, "Zildjian K Series", Category.CYMBALS, 9);

        Product updatedProduct = warehouse.getProductById(1);
        assertEquals("Zildjian K Series", updatedProduct.name(), "Expected the product name to be updated");
        assertEquals(9, updatedProduct.rating(), "Expect the product rating to be updated");
    }

    @Test
    @DisplayName("Retrieve a product by its ID successfully")
    void getProductById_success() {

        Product product = new Product(1, "Zildjian", Category.CYMBALS, 7, LocalDate.now(), LocalDate.now());
        warehouse.addProduct(product);

        Product retrievedProduct = warehouse.getProductById(1);
        assertNotNull(retrievedProduct, "Expected the product to be found");
        assertEquals("Zildjian", retrievedProduct.name(), "Expected the product name to match");
    }

    @Test
    @DisplayName("Throw exception when retrieving a product by non-existing ID")
    void getProductById_notFound_throwsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            warehouse.getProductById(0);
        });

        assertTrue(exception.getMessage().contains("Product with ID 0 not found"),
                "Expected exception message: 'Product with ID 0 not found!'");
    }

    @Test
    @DisplayName("Retrieve all products by category successfully")
    void getProductsByCategory_success() {
        Product product1 = new Product(1, "Zildjian", Category.CYMBALS, 7, LocalDate.now(), LocalDate.now());
        Product product2 = new Product(2, "Meinl", Category.CYMBALS, 8, LocalDate.now(), LocalDate.now());
        warehouse.addProduct(product1);
        warehouse.addProduct(product2);

        List<Product> cymbals = warehouse.getProductsByCategory(Category.CYMBALS);
        assertEquals(2, cymbals.size(), "Expected 2 products in the CYMBALS category");
    }

    @Test
    @DisplayName("Retrieve products that have been modified")
    void getModifiedProduct_withSuccess() {
        Product product1 = new Product(1, "Zildjian", Category.CYMBALS, 7, LocalDate.now(), LocalDate.now());
        Product product2 = new Product(2, "Meinl", Category.CYMBALS, 8, LocalDate.of(2024, 9, 14), LocalDate.of(2024, 9, 15)); // Modifierad produkt
        warehouse.addProduct(product1);
        warehouse.addProduct(product2);

        List<Product> modifiedProducts = warehouse.getModifiedProduct();
        assertEquals(1, modifiedProducts.size(), "Expected 1 modified product");
        assertEquals("Meinl", modifiedProducts.get(0).name(), "Expected 'Meinl' to be the modified product");
    }


}