package org.projeto.domain.repositories;

import org.projeto.domain.entities.Wishlist;

import java.util.Optional;

public interface WishlistRepository {
    void deleteByCustomerId(String customerId);

    Optional<Wishlist> findByCustomerId(String customerId);

    void save(Wishlist wishlist);
}
