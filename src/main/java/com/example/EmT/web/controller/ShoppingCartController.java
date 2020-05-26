package com.example.EmT.web.controller;

import com.example.EmT.model.ShoppingCart;
import com.example.EmT.service.AuthService;
import com.example.EmT.service.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shopping-carts")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final AuthService authService;

    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  AuthService authService) {
        this.shoppingCartService = shoppingCartService;
        this.authService = authService;
    }


    @PostMapping("/{bookId}/add-book")
    public String addBookToShoppingCart(@PathVariable Long bookId) {
        try {
            ShoppingCart shoppingCart = this.shoppingCartService.addBookToShoppingCart(this.authService.getCurrentUserId(), bookId);
        } catch (RuntimeException ex) {
            return "redirect:/books?error=" + ex.getLocalizedMessage();
        }
        return "redirect:/books";
    }


    @PostMapping("/{bookId}/remove-book")
    public String removeBookToShoppingCart(@PathVariable Long bookId) {
        ShoppingCart shoppingCart = this.shoppingCartService.removeBookFromShoppingCart(this.authService.getCurrentUserId(), bookId);
        return "redirect:/books";
    }
}
