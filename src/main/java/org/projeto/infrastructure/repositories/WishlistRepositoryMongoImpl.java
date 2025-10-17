package org.projeto.infrastructure.repositories;


import org.projeto.domain.entities.Wishlist;
import org.projeto.domain.repositories.WishlistRepository;
import org.projeto.infrastructure.persistence.WishlistDocument;
import org.projeto.infrastructure.persistence.WishlistMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class WishlistRepositoryMongoImpl implements WishlistRepository {

    private final WishlistMongoSpringData mongoRepo;
    private final WishlistMapper wishlistMapper;

    public WishlistRepositoryMongoImpl(WishlistMongoSpringData mongoRepo, WishlistMapper wishlistMapper) {
        this.mongoRepo = mongoRepo;
        this.wishlistMapper = wishlistMapper;
    }

    @Override
    public void deleteByCustomerId(String customerId) {
        mongoRepo.deleteByCustomerId(customerId);
    }

    @Override
    public Optional<Wishlist> findByCustomerId(String customerId) {
        return mongoRepo.findByCustomerId(customerId)
                .map(wishlistMapper::toDomain);
    }

    @Override
    public void save(Wishlist wishlist) {
        WishlistDocument doc = wishlistMapper.toDocument(wishlist);
        mongoRepo.save(doc);
    }
}