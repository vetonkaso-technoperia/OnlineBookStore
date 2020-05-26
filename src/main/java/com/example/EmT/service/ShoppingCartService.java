package com.example.EmT.service;

import com.example.EmT.model.ShoppingCart;
import com.example.EmT.model.dto.ChargeRequest;

import java.util.List;

public interface ShoppingCartService {

    ShoppingCart findActiveShoppingCartByUsername(String userId);

    List<ShoppingCart> findAllByUsername(String userId);

    ShoppingCart createNewShoppingCart(String userId);

    ShoppingCart addBookToShoppingCart(String userId,
                                          Long bookId);

    ShoppingCart removeBookFromShoppingCart(String userId,
                                               Long bookId);

    ShoppingCart getActiveShoppingCart(String userId);

    ShoppingCart cancelActiveShoppingCart(String userId);

    ShoppingCart checkoutShoppingCart(String userId, ChargeRequest chargeRequest);

  ShoppingCart checkoutActiveShoppingCart(String currentUserId);
}
