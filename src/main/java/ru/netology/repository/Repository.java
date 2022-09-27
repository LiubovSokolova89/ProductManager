package ru.netology.repository;

import ru.netology.already.exists.exception.AlreadyExistsException;
import ru.netology.not.found.exception.NotFoundException;
import ru.netology.product.Product;

public class Repository {
    private Product[] products = new Product[0];

    public Repository() {
    }


    public void getAllSavedProducts(Product product) {
        if (findById(product.getId()) != null) {
            throw new AlreadyExistsException("Товар с таким Id" + product.getId() + "уже существует");
        }
        Product[] tmp = new Product[products.length + 1];
        for (int i = 0; i < products.length; i++) {
            tmp[i] = products[i];
        }
        tmp[tmp.length - 1] = product;
        products = tmp;
    }

    public void removeById(int id) {
        if (findById(id) == null) {
            throw new NotFoundException("Товар с Id" + id + "Не найдено");
        }
        Product[] tmp = new Product[products.length - 1];
        int copyToIndex = 0;
        for (Product product : products) {
            if (product.getId() != id) {
                tmp[copyToIndex] = product;
                copyToIndex++;
            }
        }
        products = tmp;
    }

    public Product findById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }


    public Product[] findAll() {
        return products;
    }
}
