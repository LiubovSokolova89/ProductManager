package ru.netology.manager;

import ru.netology.data.Product;
import ru.netology.repository.Repository;

public class Manager {
    protected Repository repo;

    public Manager(Repository repo) {
        this.repo = repo;
    }


    public void add(Product product) {
        repo.getAllSavedProducts(product);
    }

    public Product[] findAll() {
        return repo.findAll();
    }


    public Product[] searchBy(String text) {
        Product[] result = new Product[0];
        for (Product product : repo.findAll()) {
            if (matches(product, text)) {
                Product[] tmp = new Product[result.length + 1];
                for (int i = 0; i < result.length; i++) {
                    tmp[i] = result[i];
                }
                tmp[tmp.length - 1] = product;
                result = tmp;
            }
        }
        return result;
    }

    public boolean matches(Product product, String search) {
        if (product.getName().contains(search)) {
            return true;
        } else {
            return false;
        }
        // или в одну строку:
        // return product.getName().contains(search);
    }

    public int getPrice(Product product) {
        return product.getPrice();
    }
}




