package org.projeto.infrastructure.repositories;

import org.projeto.infrastructure.persistence.WishlistDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface WishlistMongoSpringData extends MongoRepository<WishlistDocument, String> {
    void deleteByCustomerId(String customerId);
    Optional<WishlistDocument> findByCustomerId(String customerId);
}