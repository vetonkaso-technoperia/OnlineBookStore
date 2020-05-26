package com.example.EmT.service.impl;

import com.example.EmT.model.exception.BookIsAlreadyInShoppingCartException;
import com.example.EmT.model.exception.BookOutOfStockException;
import com.example.EmT.model.exception.ShoppingCartIsAlreadyCreated;
import com.example.EmT.model.exception.ShoppingCartIsNotActiveException;
import com.example.EmT.model.exception.TransactionFailedException;
import com.example.EmT.repository.ShoppingCartRepository;
import com.example.EmT.model.Book;
import com.example.EmT.model.ShoppingCart;
import com.example.EmT.model.User;
import com.example.EmT.model.dto.ChargeRequest;
import com.example.EmT.model.enumerations.CartStatus;
import com.example.EmT.model.exception.*;
import com.example.EmT.service.PaymentService;
import com.example.EmT.service.BookService;
import com.example.EmT.service.ShoppingCartService;
import com.example.EmT.service.UserService;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final UserService userService;
    private final BookService bookService;
    private final PaymentService paymentService;

    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartServiceImpl(UserService userService,
                                   BookService bookService,
                                   PaymentService paymentService,
                                   ShoppingCartRepository shoppingCartRepository) {
        this.userService = userService;
        this.bookService = bookService;
        this.paymentService = paymentService;
        this.shoppingCartRepository = shoppingCartRepository;
    }


    @Override
    public ShoppingCart findActiveShoppingCartByUsername(String userId) {
        return this.shoppingCartRepository.findByUserUsernameAndStatus(userId, CartStatus.CREATED)
                .orElseThrow(() -> new ShoppingCartIsNotActiveException(userId));
    }

    @Override
    public List<ShoppingCart> findAllByUsername(String userId) {
        return this.shoppingCartRepository.findAllByUserUsername(userId);
    }

    @Override
    public ShoppingCart createNewShoppingCart(String userId) {
        User user = this.userService.findById(userId);
        if (this.shoppingCartRepository.existsByUserUsernameAndStatus(
                user.getUsername(),
                CartStatus.CREATED
        )) {
            throw new ShoppingCartIsAlreadyCreated(userId);
        }
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        return this.shoppingCartRepository.save(shoppingCart);
    }

    @Override
    @Transactional
    public ShoppingCart addBookToShoppingCart(String userId, Long bookId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(userId);
        Book book = this.bookService.findById(bookId);
        for (Book p : shoppingCart.getBooks()) {
            if (p.getId().equals(bookId)) {
                throw new BookIsAlreadyInShoppingCartException(book.getName());
            }
        }
        shoppingCart.getBooks().add(book);
        return this.shoppingCartRepository.save(shoppingCart);
    }

    @Override
    @Transactional
    public ShoppingCart removeBookFromShoppingCart(String userId, Long bookId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(userId);
        shoppingCart.setBooks(
                shoppingCart.getBooks()
                        .stream()
                        .filter(book -> !book.getId().equals(bookId))
                        .collect(Collectors.toList())
        );
        return this.shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String userId) {
        return this.shoppingCartRepository
                .findByUserUsernameAndStatus(userId, CartStatus.CREATED)
                .orElseGet(() -> {
                    ShoppingCart shoppingCart = new ShoppingCart();
                    User user = this.userService.findById(userId);
                    shoppingCart.setUser(user);
                    return this.shoppingCartRepository.save(shoppingCart);
                });
    }

    @Override
    public ShoppingCart cancelActiveShoppingCart(String userId) {
        ShoppingCart shoppingCart = this.shoppingCartRepository
                .findByUserUsernameAndStatus(userId, CartStatus.CREATED)
                .orElseThrow(() -> new ShoppingCartIsNotActiveException(userId));
        shoppingCart.setStatus(CartStatus.CANCELED);
        return this.shoppingCartRepository.save(shoppingCart);
    }

    @Override
    @Transactional
    public ShoppingCart checkoutShoppingCart(String userId, ChargeRequest chargeRequest) {
        ShoppingCart shoppingCart = this.shoppingCartRepository
                .findByUserUsernameAndStatus(userId, CartStatus.CREATED)
                .orElseThrow(() -> new ShoppingCartIsNotActiveException(userId));

        List<Book> books = shoppingCart.getBooks();
        float price = 0;

        for (Book book : books) {
            if (book.getQuantity() <= 0) {
                throw new BookOutOfStockException(book.getName());
            }
            book.setQuantity(book.getQuantity() - 1);
            price += book.getPrice();
        }
        Charge charge = null;
        try {
            charge = this.paymentService.pay(chargeRequest);
        } catch (CardException | APIException | AuthenticationException | APIConnectionException | InvalidRequestException e) {
            throw new TransactionFailedException(userId, e.getMessage());
        }

        shoppingCart.setBooks(books);
        shoppingCart.setStatus(CartStatus.FINISHED);
        return this.shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart checkoutActiveShoppingCart(String currentUserId) {
        return null;
    }

}
