package ru.netology.repository;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.netology.already.exists.exception.AlreadyExistsException;
import ru.netology.book.Book;
import ru.netology.manager.Manager;
import ru.netology.not.found.exception.NotFoundException;
import ru.netology.product.Product;
import ru.netology.smartphone.Smartphone;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;
import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;

public class RepositoryTest {


    Repository repo = new Repository();
    Manager manager = new Manager(repo);


    Product product1 = new Book(2345 , "Book" , 567 , "Flex");
    Product product2 = new Smartphone(568, "Phone1" , 87000 , "Phone");
    Product product3 = new Product(65 , "Dress", 1000);
    Product product4 = new Smartphone(90 , "Phone14" , 115000 , "Phone");
    Product product5 = new Smartphone(4756 , "Phone10" , 50000 , "Slime");


    @Test
    public void RemoveById() {
        Repository repo = new Repository();
        repo.getAllSavedProducts(product1);
        repo.getAllSavedProducts(product2);
        repo.getAllSavedProducts(product3);
        repo.getAllSavedProducts(product4);
        repo.getAllSavedProducts(product5);

        repo.removeById(568);
        Product[] actual = repo.findAll();
        Product[] expected = {product1 , product3 , product4 , product5};

    }

    @Test
    public void removeNonExistentId() {
        Repository repo = new Repository();
        repo.getAllSavedProducts(product1);
        repo.getAllSavedProducts(product2);
        repo.getAllSavedProducts(product3);
        repo.getAllSavedProducts(product4);
        repo.getAllSavedProducts(product5);


        assertThrows(NotFoundException.class, () -> {
            repo.removeById(1);
        });

    }

    @Test
    public void addId() {
        Product product6 = new Product(21 , "Phone11" , 40000);
        repo.getAllSavedProducts(product1);
        repo.getAllSavedProducts(product2);
        repo.getAllSavedProducts(product3);
        repo.getAllSavedProducts(product4);
        repo.getAllSavedProducts(product5);
        repo.getAllSavedProducts(product6);

        Product[] expected = new Product[] {
                product1,
                product2,
                product3,
                product4,
                product5,
                product6
        };

        Product[] actual = repo.findAll();

        assertArrayEquals(expected , actual);

    }

    @Test
    public void throwingAlreadyExistsExceptionWhenTryingAddElementWithDuplicateId() {
        Repository repo = new Repository();
        Product product6 = new Product(65 , "pen" , 5000);
        repo.getAllSavedProducts(product1);
        repo.getAllSavedProducts(product2);
        repo.getAllSavedProducts(product3);
        repo.getAllSavedProducts(product4);
        repo.getAllSavedProducts(product5);


        assertThrows(AlreadyExistsException.class, () -> {
            repo.getAllSavedProducts(product6);
        });
    }

}

