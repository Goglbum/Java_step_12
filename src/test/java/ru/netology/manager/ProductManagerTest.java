package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class ProductManagerTest {
    @Mock
    private ProductRepository repository;
    @InjectMocks
    private ProductManager manager;
    Product item1 = new Book(1, "Python Tricks", 50, "Dan Bader");
    Product item2 = new Book(2, "The Complete Software Developer's Career Guide", 50, "John");
    Product item3 = new Book(3, "Hacking", 50, "John");
    Product item4 = new Smartphone(4, "iPhone 11", 50, "Apple");
    Product item5 = new Smartphone(5, "Galaxy A51", 50, "Samsung");
    Product item6 = new Smartphone(6, "Redmi Note 9", 50, "Xiaomi");

    @BeforeEach
    public void setUp() {
        Product[] returned = new Product[]{item1, item2, item3, item4, item5, item6};
        doReturn(returned).when(repository).findAll();
    }

    @Test
    public void searchByTwoBookAuthor() {
        Product[] actual = manager.searchBy("John");
        Product[] expected = new Product[]{item2, item3};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void searchByBookName() {
        Product[] actual = manager.searchBy("Hacking");
        Product[] expected = new Product[]{item3};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void searchBySmartphoneName() {
        Product[] actual = manager.searchBy("Galaxy A51");
        Product[] expected = new Product[]{item5};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void searchBySmartphoneManufacturer() {
        Product[] actual = manager.searchBy("Xiaomi");
        Product[] expected = new Product[]{item6};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void searchByNonItem() {
        Product[] actual = manager.searchBy("Honor");
        Product[] expected = new Product[0];
        assertArrayEquals(expected, actual);
    }
}