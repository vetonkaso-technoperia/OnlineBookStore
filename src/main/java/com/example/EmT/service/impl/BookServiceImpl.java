package com.example.EmT.service.impl;

import com.example.EmT.model.Category;
import com.example.EmT.service.AuthorService;
import com.example.EmT.model.Author;
import com.example.EmT.model.Book;
import com.example.EmT.model.exception.BookIsAlreadyInShoppingCartException;
import com.example.EmT.model.exception.BookNotFoundException;
import com.example.EmT.repository.BookRepository;
import com.example.EmT.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
  private BookServiceImpl categoryService;

  public BookServiceImpl(BookRepository bookRepository,
        AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public List<Book> findAllByAuthorId(Long authorId) {
        return null;
    }


    @Override
    public List<Book> findAllSortedByPrice(boolean asc) {
        if (asc) {
            return this.bookRepository.findAllByOrderByPriceAsc();
        }
        return this.bookRepository.findAllByOrderByPriceDesc();
    }

  @Override
  public List<Book> findAllByCategoryId(Long categoryId) {
    return this.bookRepository.findAllByCategoryId(categoryId);
  }


  @Override
    public Book findById(Long id) {
        return this.bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

  @Override
  public Book saveBook(String name, Float price, Integer quantity, Long authorId) {
    return null;
  }

  @Override
  public Book save(Book book, MultipartFile image) throws IOException{
    Book category = this.categoryService.findById(book.getCategory().getId());
    book.setCategory(category);
    if (image != null && !image.getName().isEmpty()) {
      byte[] bytes = image.getBytes();
      String base64Image = String.format("data:%s;base64,%s", image.getContentType(), Base64.getEncoder().encodeToString(bytes));
      book.setImageBase64(base64Image);
    }
    return this.bookRepository.save(book);
  }

  @Override
  public Book update(Long id, Book books, MultipartFile image) throws IOException {
    Book book = this.findById(id);
    Book category = this.categoryService.findById(books.getCategory().getId());
    book.setCategory(book.getCategory());
    book.setId(book.getId());
    book.setQuantity(book.getQuantity());
    book.setName(book.getName());
    if(image!=null && !image.getName().isEmpty()){
      byte[] bytes = image.getBytes();
      String base64Image = String.format("data:%s;base64,%s", image.getContentType(), Base64.getEncoder().encodeToString(bytes));
      book.setImageBase64(base64Image);
    }
    return this.bookRepository.save(book);
  }

    @Override
    public Book saveBook(Book book, MultipartFile image) throws IOException {
        Author author = this.authorService.findById(book.getAuthor().getId());
        book.setAuthor(author);
        if (image != null && !image.getName().isEmpty()) {
            byte[] bytes = image.getBytes();
            String base64Image = String.format("data:%s;base64,%s", image.getContentType(), Base64.getEncoder().encodeToString(bytes));
//            book.setImage(image);
            book.setImageBase64(base64Image);
        }
        return this.bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long id, Book book, MultipartFile image) throws IOException {
        Book p = this.findById(id);
        Author author = this.authorService.findById(book.getAuthor().getId());
        p.setAuthor(author);
        p.setPrice(book.getPrice());
        p.setQuantity(book.getQuantity());
        if (image != null && !image.getName().isEmpty()) {
            byte[] bytes = image.getBytes();
            String base64Image = String.format("data:%s;base64,%s", image.getContentType(), Base64.getEncoder().encodeToString(bytes));
            p.setImageBase64(base64Image);
        }
        return this.bookRepository.save(p);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Book book = this.findById(id);
        if (book.getShoppingCarts().size() > 0) {
            //avoid deleting book that is already in shopping cart!
            throw new BookIsAlreadyInShoppingCartException(book.getName());
        }

//        other solution is to break the relationship between that book and the shopping cart
//        book.getShoppingCarts().forEach(shoppingCart -> shoppingCart.setBooks(
//                shoppingCart.getBooks()
//                .stream()
//                .filter(p -> !p.getId().equals(id))
//                .collect(Collectors.toList())
//        ));
        this.bookRepository.deleteById(id);
    }
}
