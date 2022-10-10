package ru.netology.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.already.exists.exception.AlreadyExistsException;
import ru.netology.data.Book;
import ru.netology.data.Product;
import ru.netology.data.Smartphone;
import ru.netology.manager.Manager;
import ru.netology.not.found.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class RepositoryTest {


    Repository repo = new Repository();
    Manager manager = new Manager(repo);


    Product product1 = new Book(2345, "Book", 567, "Flex");
    Product product2 = new Smartphone(568, "Phone1", 87000, "Phone");
    Product product3 = new Product(65, "Dress", 1000);
    Product product4 = new Smartphone(90, "Phone14", 115000, "Phone");
    Product product5 = new Smartphone(4756, "Phone10", 50000, "Slime");

    @BeforeEach
    public void setup() {
        repo.getAllSavedProducts(product1);
        repo.getAllSavedProducts(product2);
        repo.getAllSavedProducts(product3);
        repo.getAllSavedProducts(product4);
        repo.getAllSavedProducts(product5);

    }


    @Test
    public void RemoveById() {
        repo.removeById(568);
        Product[] actual = repo.findAll();
        Product[] expected = {product1, product3, product4, product5};

    }

    @Test
    public void removeNonExistentId() {

        assertThrows(NotFoundException.class, () -> {
            repo.removeById(1);
        });

    }

    @Test
    public void addId() {
        Product product6 = new Product(21, "Phone11", 40000);
        repo.getAllSavedProducts(product6);

        Product[] expected = new Product[]{
                product1,
                product2,
                product3,
                product4,
                product5,
                product6
        };

        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void throwingAlreadyExistsExceptionWhenTryingAddElementWithDuplicateId() {
        Product product6 = new Product(65, "pen", 5000);

        assertThrows(AlreadyExistsException.class, () -> {
            repo.getAllSavedProducts(product6);
        });
    }

}