package ru.netology.manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.book.Book;
import ru.netology.product.Product;
import ru.netology.repository.Repository;
import ru.netology.smartphone.Smartphone;

public class ManagerTest {

        Repository repo = new Repository();
        Manager manager = new Manager(repo);

        Product Book1 = new Book(123, "Скала" , 500, "Шевченко");
        Product Book2 = new Book(657, "Лето на озере" , 456, "Шевченко");
        Product Book3 = new Book(483, "Марс" , 674, "Коваль");
        Product Smartphone1 = new Smartphone(8954, "Orange 10" , 56000, "Orange");
        Product Smartphone2 = new Smartphone(47565, "Horse 11" , 34000, "Horse");
        Product Smartphone3 = new Smartphone(347457, "Horse 9" , 25000, "Horse");


    @Test
    public void ShowAddByName() {

        manager.add(Book1);
        manager.add(Book2);
        manager.add(Book3);
        manager.add(Smartphone1);
        manager.add(Smartphone2);
        manager.add(Smartphone3);

        Product[] actual = manager.searchBy( "Horse");
        Product[] expected = {Smartphone2, Smartphone3};

        Assertions.assertArrayEquals(expected , actual);


    }

    @Test
    public void ShouldFindNothingIfTheManagerIsEmpty() {
         Product[] actual = manager.searchBy("Horse");
         Product[] expected = {};
    }

    @Test
    public void ShouldRemoveById() {

        manager.add(Book1);
        manager.add(Book2);
        manager.add(Book3);
        manager.add(Smartphone1);
        manager.add(Smartphone2);
        manager.add(Smartphone3);

        repo.removeById(Book1.getId());

        Product[] actual = manager.findAll();
        Product[] expected = {Book2 , Book3, Smartphone1, Smartphone2, Smartphone3};


    }

}
