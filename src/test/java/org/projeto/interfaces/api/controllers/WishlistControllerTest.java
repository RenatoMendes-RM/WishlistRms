package org.projeto.interfaces.api.controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
        import org.projeto.application.services.WishlistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;

class WishlistControllerTest {

    @InjectMocks
    private WishlistController wishlistController;

    @Mock
    private WishlistService wishlistService;

    private AutoCloseable closeableMocks;

    @BeforeEach
    void setUp() {
        closeableMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void closeController() throws Exception {
        closeableMocks.close();
    }

    @Test
    void addProduct_shouldReturnCreated() {
        String customerId = "c1";
        String productId = "p1";

        ResponseEntity<Void> response = wishlistController.addProduct(customerId, productId);

        verify(wishlistService).addProduct(customerId, productId);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void removeProduct_shouldReturnNoContent() {
        String customerId = "c1";
        String productId = "p1";

        ResponseEntity<Void> response = wishlistController.removeProduct(customerId, productId);

        verify(wishlistService).removeProduct(customerId, productId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void getAllProducts_shouldReturnProductsSet() {
        String customerId = "c1";
        Set<String> products = Set.of("p1", "p2");
        when(wishlistService.getAllProducts(customerId)).thenReturn(products);

        ResponseEntity<Set<String>> response = wishlistController.getAllProducts(customerId);

        verify(wishlistService).getAllProducts(customerId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products, response.getBody());
    }

    @Test
    void containsProduct_shouldReturnBoolean() {
        String customerId = "c1";
        String productId = "p1";
        when(wishlistService.containsProduct(customerId, productId)).thenReturn(true);

        ResponseEntity<Boolean> response = wishlistController.containsProduct(customerId, productId);

        verify(wishlistService).containsProduct(customerId, productId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Boolean.TRUE, response.getBody());
    }
}