package org.projeto.application.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.projeto.domain.entities.Wishlist;
import org.projeto.domain.exceptions.BusinessException;
import org.projeto.domain.exceptions.NotFoundException;
import org.projeto.domain.repositories.WishlistRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class WishlistServiceTest {

    private WishlistRepository wishlistRepository;
    private WishlistService wishlistService;

    @BeforeEach
    void setUp() {
        wishlistRepository = mock(WishlistRepository.class);
        wishlistService = new WishlistService(wishlistRepository);
    }

    @Test
    void addProduct_shouldAddProduct_whenWishlistIsNew() {
        String customerId = "c1";
        String productId = "p1";
        when(wishlistRepository.findByCustomerId(customerId)).thenReturn(Optional.empty());

        wishlistService.addProduct(customerId, productId);

        verify(wishlistRepository).save(argThat(w ->
                w.getCustomerId().equals(customerId) && w.getProductIds().contains(productId)
        ));
    }

    @Test
    void addProduct_shouldThrow_whenWishlistLimitReached() {
        String customerId = "c1";
        Set<String> products = new HashSet<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"));
        Wishlist wishlist = new Wishlist("w1", customerId, products);
        when(wishlistRepository.findByCustomerId(customerId)).thenReturn(Optional.of(wishlist));

        BusinessException ex = assertThrows(BusinessException.class, () ->
                wishlistService.addProduct(customerId, "21")
        );
        assertEquals("Wishlist limit reached", ex.getMessage());
    }

    @Test
    void addProduct_shouldThrow_whenProductAlreadyExists() {
        String customerId = "c1";
        String productId = "p1";
        Set<String> products = new HashSet<>(Collections.singletonList(productId));
        Wishlist wishlist = new Wishlist("w1", customerId, products);
        when(wishlistRepository.findByCustomerId(customerId)).thenReturn(Optional.of(wishlist));

        BusinessException ex = assertThrows(BusinessException.class, () ->
                wishlistService.addProduct(customerId, productId)
        );
        assertEquals("Product already in wishlist", ex.getMessage());
    }

    @Test
    void removeProduct_shouldRemoveProduct_whenExists() {
        String customerId = "c1";
        String productId = "p1";
        Set<String> products = new HashSet<>(Collections.singletonList(productId));
        Wishlist wishlist = new Wishlist("w1", customerId, products);
        when(wishlistRepository.findByCustomerId(customerId)).thenReturn(Optional.of(wishlist));

        wishlistService.removeProduct(customerId, productId);

        verify(wishlistRepository).save(argThat(w -> !w.getProductIds().contains(productId)));
    }

    @Test
    void removeProduct_shouldThrow_whenWishlistNotFound() {
        String customerId = "c1";
        when(wishlistRepository.findByCustomerId(customerId)).thenReturn(Optional.empty());

        NotFoundException ex = assertThrows(NotFoundException.class, () ->
                wishlistService.removeProduct(customerId, "p1")
        );
        assertEquals("Wishlist not found", ex.getMessage());
    }

    @Test
    void removeProduct_shouldThrow_whenProductNotInWishlist() {
        String customerId = "c1";
        Wishlist wishlist = new Wishlist("w1", customerId, new HashSet<>());
        when(wishlistRepository.findByCustomerId(customerId)).thenReturn(Optional.of(wishlist));

        NotFoundException ex = assertThrows(NotFoundException.class, () ->
                wishlistService.removeProduct(customerId, "p1")
        );
        assertEquals("Product not found in wishlist", ex.getMessage());
    }

    @Test
    void getAllProducts_shouldReturnProducts_whenWishlistExists() {
        String customerId = "c1";
        Set<String> products = new HashSet<>(Arrays.asList("p1", "p2"));
        Wishlist wishlist = new Wishlist("w1", customerId, products);
        when(wishlistRepository.findByCustomerId(customerId)).thenReturn(Optional.of(wishlist));

        Set<String> result = wishlistService.getAllProducts(customerId);

        assertEquals(products, result);
    }

    @Test
    void getAllProducts_shouldReturnEmptySet_whenWishlistNotFound() {
        String customerId = "c1";
        when(wishlistRepository.findByCustomerId(customerId)).thenReturn(Optional.empty());

        Set<String> result = wishlistService.getAllProducts(customerId);

        assertTrue(result.isEmpty());
    }

    @Test
    void containsProduct_shouldReturnTrue_whenProductExists() {
        String customerId = "c1";
        String productId = "p1";
        Set<String> products = new HashSet<>(Collections.singletonList(productId));
        Wishlist wishlist = new Wishlist("w1", customerId, products);
        when(wishlistRepository.findByCustomerId(customerId)).thenReturn(Optional.of(wishlist));

        assertTrue(wishlistService.containsProduct(customerId, productId));
    }

    @Test
    void containsProduct_shouldReturnFalse_whenProductNotExists() {
        String customerId = "c1";
        String productId = "p1";
        Wishlist wishlist = new Wishlist("w1", customerId, new HashSet<>());
        when(wishlistRepository.findByCustomerId(customerId)).thenReturn(Optional.of(wishlist));

        assertFalse(wishlistService.containsProduct(customerId, productId));
    }

    @Test
    void containsProduct_shouldReturnFalse_whenWishlistNotFound() {
        String customerId = "c1";
        String productId = "p1";
        when(wishlistRepository.findByCustomerId(customerId)).thenReturn(Optional.empty());

        assertFalse(wishlistService.containsProduct(customerId, productId));
    }
}