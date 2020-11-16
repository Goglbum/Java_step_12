package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    private ProductRepository repository = new ProductRepository();
    Product item1 = new Book(1, "Python Tricks", 50, "Dan Bader");
    Product item2 = new Book(2, "The Complete Software Developer's Career Guide", 50, "John");
    Product item3 = new Book(3, "Hacking", 50, "John");
    Product item4 = new Smartphone(4, "iPhone 11", 50, "Apple");
    Product item5 = new Smartphone(5, "Galaxy A51", 50, "Samsung");
    Product item6 = new Smartphone(6, "Redmi Note 9", 50, "Xiaomi");

    @BeforeEach
    public void setUp() {
        repository.save(item1);
        repository.save(item2);
        repository.save(item3);
        repository.save(item4);
        repository.save(item5);
        repository.save(item6);
    }

    @Test
    public void removeById() {
        repository.removeById(4);
        Product[] expected = new Product[]{item1, item2, item3, item5, item6};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void removeByNonExistId() {
        repository.removeById(7);
        Product[] expected = new Product[]{item1, item2, item3, item4, item5, item6};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }
}