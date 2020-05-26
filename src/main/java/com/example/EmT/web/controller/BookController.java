package com.example.EmT.web.controller;

import com.example.EmT.model.Author;
import com.example.EmT.model.Book;
import com.example.EmT.model.exception.BookIsAlreadyInShoppingCartException;
import com.example.EmT.service.AuthorService;
import com.example.EmT.service.BookService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {


    private BookService bookService;
    private AuthorService authorService;

    public BookController(BookService bookService,
                             AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    //    @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public String getBookPage(Model model) {
        List<Book> books = this.bookService.findAll();
        model.addAttribute("books", books);
        return "books";
    }


    //    @RequestMapping(method = RequestMethod.GET, value = "/add-new")
    @GetMapping("/add-new")
    public String addNewBookPage(Model model) {
        List<Author> authors = this.authorService.findAll();
        model.addAttribute("authors", authors);
        model.addAttribute("book", new Book());
        return "add-book";
    }

    @GetMapping("/{id}/edit")
    public String editBookPage(Model model, @PathVariable Long id) {
        try {
            Book book = this.bookService.findById(id);
            List<Author> authors = this.authorService.findAll();
            model.addAttribute("book", book);
            model.addAttribute("authors", authors);
            return "add-book";
        } catch (RuntimeException ex) {
            return "redirect:/books?error=" + ex.getMessage();
        }
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    public String saveBook(
//            @RequestParam String name,
//                              @RequestParam Float price,
//                              @RequestParam Integer quantity,
//                              @RequestParam Long authorId,
            @Valid Book book,
            BindingResult bindingResult,
            @RequestParam MultipartFile image,
            Model model) {

        if (bindingResult.hasErrors()) {
            List<Author> authors = this.authorService.findAll();
            model.addAttribute("authors", authors);
            return "add-book";
        }
        try {
            this.bookService.saveBook(book, image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //this.bookService.saveBook(name, price, quantity, authorId);
//        List<Book> books = this.bookService.findAll();
//        model.addAttribute("books", books);
//        return "books";
        return "redirect:/books";
    }

    @PostMapping("/{id}/delete")
    public String deleteBookWithPost(@PathVariable Long id) {
        try {
            this.bookService.deleteById(id);
        } catch (BookIsAlreadyInShoppingCartException ex) {
            return String.format("redirect:/books?error=%s", ex.getMessage());
        }
        return "redirect:/books";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteBookWithDelete(@PathVariable Long id) {
        this.bookService.deleteById(id);
        return "redirect:/books";
    }
}
