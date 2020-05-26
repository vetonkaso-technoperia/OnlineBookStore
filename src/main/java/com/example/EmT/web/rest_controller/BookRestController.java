package com.example.EmT.web.rest_controller;

import com.example.EmT.model.Book;
import com.example.EmT.service.BookService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookRestController {
    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    @Secured("ROLE_ADMIN")
    public List<Book> findAll() {
        return this.bookService.findAll();
    }

    @GetMapping("/{id}")
    public Book findById(@PathVariable Long id) {
        return this.bookService.findById(id);
    }

    @GetMapping("/by-author/{authorId}")
    public List<Book> findAllByAuthorId(@PathVariable Long authorId) {
        return this.bookService.findAllByAuthorId(authorId);
    }

    @GetMapping("/by-price")
    public List<Book> findAllSortedByPrice(
            @RequestParam (required = false, defaultValue = "true") Boolean asc) {
        return this.bookService.findAllSortedByPrice(asc);
    }


    @PostMapping
    public Book save(@Valid Book book, @RequestParam(required = false) MultipartFile image) throws IOException {
        return this.bookService.saveBook(book, image);
    }


    @PutMapping("/{id}")
    public Book update(@PathVariable Long id,
                          @Valid Book book,
                          @RequestParam(required = false) MultipartFile image) throws IOException {
        return this.bookService.updateBook(id, book, image);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.bookService.deleteById(id);
    }

}
