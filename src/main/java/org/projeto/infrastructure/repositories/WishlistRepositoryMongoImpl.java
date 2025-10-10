package org.projeto.infrastructure.repositories;

import org.projeto.domain.entities.Wishlist;
import org.projeto.domain.repositories.WishlistRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class WishlistRepositoryMongoImpl implements WishlistRepository {
    private final WishlistMongoSpringData mongoRepo;

    public WishlistRepositoryMongoImpl(WishlistMongoSpringData mongoRepo) {
        this.mongoRepo = mongoRepo;
    }

    @Override
    public void deleteByCustomerId(String customerId) {
        mongoRepo.deleteByCustomerId(customerId);
    }

    @Override
    public Optional<Wishlist> findByCustomerId(String customerId) {
        return mongoRepo.findByCustomerId(customerId);
    }

    @Override
    public void save(Wishlist wishlist) {
        mongoRepo.save(wishlist);
    }
}