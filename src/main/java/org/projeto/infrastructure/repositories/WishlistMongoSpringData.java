package org.projeto.infrastructure.repositories;

import org.projeto.domain.entities.Wishlist;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface WishlistMongoSpringData extends MongoRepository<Wishlist, String> {
    void deleteByCustomerId(String customerId);
    Optional<Wishlist> findByCustomerId(String customerId);
}