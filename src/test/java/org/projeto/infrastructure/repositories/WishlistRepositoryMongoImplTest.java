package org.projeto.infrastructure.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.projeto.domain.entities.Wishlist;
import org.projeto.infrastructure.persistence.WishlistDocument;
import org.projeto.infrastructure.persistence.WishlistMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class WishlistRepositoryMongoImplTest {

    private WishlistMongoSpringData mongoRepo;
    private WishlistMapper wishlistMapper;
    private WishlistRepositoryMongoImpl repository;

    @BeforeEach
    void setUp() {
        mongoRepo = mock(WishlistMongoSpringData.class);
        wishlistMapper = mock(WishlistMapper.class);
        repository = new WishlistRepositoryMongoImpl(mongoRepo, wishlistMapper);
    }

    @Test
    void testDeleteByCustomerId() {
        String customerId = "cust123";
        repository.deleteByCustomerId(customerId);
        verify(mongoRepo, times(1)).deleteByCustomerId(customerId);
    }

    @Test
    void testFindByCustomerId_Found() {
        String customerId = "cust123";
        WishlistDocument doc = mock(WishlistDocument.class);
        Wishlist wishlist = mock(Wishlist.class);

        when(mongoRepo.findByCustomerId(customerId)).thenReturn(Optional.of(doc));
        when(wishlistMapper.toDomain(doc)).thenReturn(wishlist);

        Optional<Wishlist> result = repository.findByCustomerId(customerId);

        assertTrue(result.isPresent());
        assertEquals(wishlist, result.get());
        verify(mongoRepo).findByCustomerId(customerId);
        verify(wishlistMapper).toDomain(doc);
    }

    @Test
    void testFindByCustomerId_NotFound() {
        String customerId = "cust123";
        when(mongoRepo.findByCustomerId(customerId)).thenReturn(Optional.empty());

        Optional<Wishlist> result = repository.findByCustomerId(customerId);

        assertFalse(result.isPresent());
        verify(mongoRepo).findByCustomerId(customerId);
        verify(wishlistMapper, never()).toDomain(any());
    }

    @Test
    void testSave() {
        Wishlist wishlist = mock(Wishlist.class);
        WishlistDocument doc = mock(WishlistDocument.class);

        when(wishlistMapper.toDocument(wishlist)).thenReturn(doc);

        repository.save(wishlist);

        verify(wishlistMapper).toDocument(wishlist);
        verify(mongoRepo).save(doc);
    }
}