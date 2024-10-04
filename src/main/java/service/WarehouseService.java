package service;

import entities.Category;
import entities.Product;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WarehouseService {
    private final Warehouse warehouse;
    private final Lock lock = new ReentrantLock();

    public WarehouseService(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public void addProduct(Product product) {
        lock.lock();
        try {
            warehouse.addProduct(product);
        } finally {
            lock.unlock();
        }
    }

    public List<Product> getAllProducts() {
        lock.lock();
        try {
            return warehouse.getProductSortedByName();
        } finally {
            lock.unlock();
        }
    }

    public Product getProductById(int id) {
        lock.lock();
        try {
            return warehouse.getProductById(id).orElse(null);
        } finally {
            lock.unlock();
        }
    }

    public List<Product> getProductByCategory(Category category) {
        lock.lock();
        try {
            return warehouse.getProductsByCategory(category);
        } finally {
            lock.unlock();
        }
    }
}
