package com.example.EmT.web.rest_controller;

import com.example.EmT.model.Author;
import com.example.EmT.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//@Controller
//@ResponseBody
@RestController
@RequestMapping("/api/authors")
public class AuthorRestController {

    private final AuthorService authorService;

    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> findAll(@RequestParam(required = false) String name) {
        if (name == null) {
            return this.authorService.findAll();
        } else {
            return this.authorService.findAllByName(name);
        }
    }

    @GetMapping("/by-name")
    public List<Author> findAllByName(@RequestParam String name) {
        return this.authorService.findAllByName(name);
    }



    @GetMapping("/{id}")
    public Author findById(@PathVariable Long id) {
        return this.authorService.findById(id);
    }

//    @PostMapping
//    public Author save(@RequestParam String name,
//                             @RequestParam String address) {
//        Author author = new Author();
//        author.setName(name);
//        author.setAddress(address);
//        return this.authorService.save(author);
//    }

    @PostMapping
    public Author save(@Valid Author author) {
            return this.authorService.save(author);
    }

//    @PostMapping
//    public Author save(@Valid @RequestBody Author author) {
//        return this.authorService.save(author);
//    }

    @PutMapping("/{id}")
    public Author update(@PathVariable Long id, @Valid Author author) {
        return this.authorService.update(id, author);
    }

    @PatchMapping("/{id}")
    public Author updateName(@PathVariable Long id, @RequestParam String name) {
        return this.authorService.updateName(id, name);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.authorService.deleteById(id);
    }


}
