package org.example;
import entities.Category;
import entities.Product;
import service.Warehouse;
import java.time.LocalDate;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();

        Product product1 = new Product(1, "Zildjan", Category.CYMBALS, 7, LocalDate.now(), LocalDate.now());
        Product product2 = new Product(2, "DW Edge", Category.SNARE_DRUMS, 9, LocalDate.now(), LocalDate.now());
        Product product3 = new Product(3, "Pearl Bass Drum", Category.BASS_DRUMS, 6, LocalDate.now(), LocalDate.now());
        Product product4 = new Product(4, "Vic Firth", Category.STICKS, 8, LocalDate.now(), LocalDate.now());
        Product product5 = new Product(5, "Axis", Category.PEDALS, 5, LocalDate.now(), LocalDate.now());
        Product product6 = new Product(6, "REMO Emperor", Category.DRUM_HEADS, 8, LocalDate.now(), LocalDate.now());
        Product product7 = new Product(7, "TAMA StarClassic", Category.TOM_TOMS, 6, LocalDate.now(), LocalDate.now());
        Product product8 = new Product(8, "Sabian", Category.CYMBALS, 10, LocalDate.now(), LocalDate.now());
        Product product9 = new Product(9, "Meinl", Category.CYMBALS, 4, LocalDate.now(), LocalDate.now());
        Product product10 = new Product(10, "Paiste", Category.CYMBALS, 8, LocalDate.now(), LocalDate.now());

        warehouse.addProduct(product1);
        warehouse.addProduct(product2);
        warehouse.addProduct(product3);
        warehouse.addProduct(product4);
        warehouse.addProduct(product5);
        warehouse.addProduct(product6);
        warehouse.addProduct(product7);
        warehouse.addProduct(product8);
        warehouse.addProduct(product9);
        warehouse.addProduct(product10);


        System.out.println("Product added successfully");
        warehouse.getAllProducts();

        System.out.println("Products sorted by name: ");
        List<Product> sortedProducts = warehouse.getProductSortedByName();
        for(Product product : sortedProducts) {
            System.out.println(product);
        }

        System.out.println("Cymbals sorted by name: ");
        List<Product> sortedCymbals = warehouse.getProductsByCategory(Category.CYMBALS);
        for(Product product : sortedCymbals) {
            System.out.println(product);
        }


    }
}
