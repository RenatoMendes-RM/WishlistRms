package org.projeto.interfaces.api.controllers;

import org.projeto.application.services.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/wishlists")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @PostMapping("/{customerId}/products/{productId}")
    public ResponseEntity<Void> addProduct(@PathVariable String customerId, @PathVariable String productId) {
        wishlistService.addProduct(customerId, productId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{customerId}/products/{productId}")
    public ResponseEntity<Void> removeProduct(@PathVariable String customerId, @PathVariable String productId) {
        wishlistService.removeProduct(customerId, productId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{customerId}/products")
    public ResponseEntity<Set<String>> getAllProducts(@PathVariable String customerId) {
        return ResponseEntity.ok(wishlistService.getAllProducts(customerId));
    }

    @GetMapping("/{customerId}/products/{productId}")
    public ResponseEntity<Boolean> containsProduct(@PathVariable String customerId, @PathVariable String productId) {
        return ResponseEntity.ok(wishlistService.containsProduct(customerId, productId));
    }
}