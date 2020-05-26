package com.example.EmT.web.rest_controller;

import com.example.EmT.model.ShoppingCart;
import com.example.EmT.service.AuthService;
import com.example.EmT.service.ShoppingCartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shopping-carts")
public class ShoppingCartRestController {

    private final ShoppingCartService shoppingCartService;
    private final AuthService authService;

    public ShoppingCartRestController(ShoppingCartService shoppingCartService,
                                      AuthService authService) {
        this.shoppingCartService = shoppingCartService;
        this.authService = authService;
    }

    @GetMapping
    public List<ShoppingCart> findAllByUsername() {
        return this.shoppingCartService.findAllByUsername(this.authService.getCurrentUserId());
    }

    @PostMapping
    public ShoppingCart createNewShoppingCart() {
        return this.shoppingCartService.createNewShoppingCart(this.authService.getCurrentUserId());
    }

    @PatchMapping("/{bookId}/books")
    public ShoppingCart addBookToCart(@PathVariable Long bookId) {
        return this.shoppingCartService.addBookToShoppingCart(
                this.authService.getCurrentUserId(),
                bookId
        );
    }

    @DeleteMapping("/{bookId}/books")
    public ShoppingCart removeBookFromCart(@PathVariable Long bookId) {
        return this.shoppingCartService.removeBookFromShoppingCart(
                this.authService.getCurrentUserId(),
                bookId
        );
    }

    @PatchMapping("/cancel")
    public ShoppingCart cancelActiveShoppingCart() {
        return this.shoppingCartService.cancelActiveShoppingCart(this.authService.getCurrentUserId());
    }

    @PatchMapping("/checkout")
    public ShoppingCart checkoutActiveShoppingCart() {
        return this.shoppingCartService.checkoutActiveShoppingCart(this.authService.getCurrentUserId());

    }



}
