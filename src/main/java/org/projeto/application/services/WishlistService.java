package org.projeto.application.services;

import org.projeto.domain.entities.Wishlist;
import org.projeto.domain.exceptions.BusinessException;
import org.projeto.domain.exceptions.NotFoundException;
import org.projeto.domain.repositories.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class WishlistService {
    private static final int MAX_PRODUCTS = 20;

    private final WishlistRepository wishlistRepository;

    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    public void addProduct(String customerId, String productId) {
        Wishlist wishlist = wishlistRepository.findByCustomerId(customerId)
                .orElse(new Wishlist(null, customerId, new HashSet<>()));

        if (wishlist.getProductIds().size() >= MAX_PRODUCTS) {
            throw new BusinessException("Wishlist limit reached");
        }
        if (wishlist.getProductIds().contains(productId)) {
            throw new BusinessException("Product already in wishlist");
        }
        wishlist.getProductIds().add(productId);
        wishlistRepository.save(wishlist);
    }

    public void removeProduct(String customerId, String productId) {
        Wishlist wishlist = wishlistRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new NotFoundException("Wishlist not found"));

        if (!wishlist.getProductIds().contains(productId)) {
            throw new NotFoundException("Product not found in wishlist");
        }
        wishlist.getProductIds().remove(productId);
        wishlistRepository.save(wishlist);
    }

    public Set<String> getAllProducts(String customerId) {

        Optional<Wishlist> byCustomerId = wishlistRepository.findByCustomerId(customerId);

        return byCustomerId
                .map(Wishlist::getProductIds)
                .orElse(Collections.emptySet());
    }

    public boolean containsProduct(String customerId, String productId) {
        return wishlistRepository.findByCustomerId(customerId)
                .map(w -> w.getProductIds().contains(productId))
                .orElse(false);
    }
}