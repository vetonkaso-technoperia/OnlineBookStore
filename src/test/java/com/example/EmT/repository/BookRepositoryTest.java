package com.example.EmT.repository;

import com.example.EmT.model.Author;
import com.example.EmT.model.Book;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    @BeforeAll
    public void init() {
        Author m = new Author();
        m.setId(1l);
        m.setName("name1");
        m = authorRepository.save(m);

        Book p1,p2,p3;
        p1 = new Book();
        p1.setQuantity(5);
        p1.setPrice(5f);
        p1.setAuthor(m);

        p2 = new Book();
        p2.setQuantity(2);
        p2.setPrice(2f);
        p2.setAuthor(m);

        p3 = new Book();
        p3.setQuantity(3);
        p3.setPrice(3f);
        p3.setAuthor(m);
        this.bookRepository.save(p1);
        this.bookRepository.save(p2);
        this.bookRepository.save(p3);
    }

    @Test
    void findAllByOrderByPriceAsc() {
        List<Book> books = this.bookRepository.findAllByOrderByPriceAsc();
        assert books.get(0).getPrice().equals(2f);
        assert books.get(1).getPrice().equals(3f);
        assert books.get(2).getPrice().equals(5f);
    }

    @Test
    void findAllByOrderByPriceDesc() {
        List<Book> books = this.bookRepository.findAllByOrderByPriceDesc();
        assert books.get(0).getPrice().equals(5f);
        assert books.get(1).getPrice().equals(3f);
        assert books.get(2).getPrice().equals(2f);
    }

    @Test
    void countAllByPriceGreaterThan() {
        long count = this.bookRepository.countAllByPriceGreaterThan(4f);
        assert count == 1;
    }

    @Test
    void findAllByAuthorId() {
        List<Book> books = this.bookRepository.findAllByAuthorId(1l);
        assert books.size() == 3;
    }

    @Test
    void findAllByNameLikeAndAuthorIdOrderByPriceDesc() {
    }
}