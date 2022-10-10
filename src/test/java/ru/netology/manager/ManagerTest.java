package ru.netology.manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ru.netology.data.Book;
import ru.netology.data.Product;
import ru.netology.data.Smartphone;
import ru.netology.repository.Repository;

public class ManagerTest {

    Repository repo = new Repository();
    Manager manager = new Manager(repo);

    Product Book1 = new Book(123, "Скала", 500, "Шевченко");
    Product Book2 = new Book(657, "Лето на озере", 456, "Шевченко");
    Product Book3 = new Book(483, "Марс", 674, "Коваль");
    Product Smartphone1 = new Smartphone(8954, "Orange 10", 56000, "Orange");
    Product Smartphone2 = new Smartphone(47565, "Horse 11", 34000, "Horse");
    Product Smartphone3 = new Smartphone(347457, "Horse 9", 25000, "Horse");

    @BeforeEach
    public void setup() {
        manager.add(Book1);
        manager.add(Book2);
        manager.add(Book3);
        manager.add(Smartphone1);
        manager.add(Smartphone2);
        manager.add(Smartphone3);
    }


    @Test
    public void ShowAddByName() {

        Product[] actual = manager.searchBy("Horse");
        Product[] expected = {Smartphone2, Smartphone3};

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void ShouldFindNothingIfTheManagerIsEmpty() {
        Product[] actual = manager.searchBy("Horse 18");
        Product[] expected = {};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void ShouldRemoveById() {
        repo.removeById(Book1.getId());

        Product[] actual = manager.findAll();
        Product[] expected = {Book2, Book3, Smartphone1, Smartphone2, Smartphone3};

    }

    @Test
    public void shouldShowPrice() {
        int expected = 25000;
        int actual = manager.getPrice(Smartphone3);

        Assertions.assertEquals(expected, actual);
    }

}