package org.projeto.infrastructure.persistence;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.projeto.domain.entities.Wishlist;

@Mapper(componentModel = "spring")
public interface WishlistMapper {
    WishlistMapper INSTANCE = Mappers.getMapper(WishlistMapper.class);

    WishlistDocument toDocument(Wishlist wishlist);

    Wishlist toDomain(WishlistDocument document);
}