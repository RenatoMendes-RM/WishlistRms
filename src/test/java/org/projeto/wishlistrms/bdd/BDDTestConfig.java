package org.projeto.wishlistrms.bdd;

import org.mockito.Mockito;
import org.projeto.domain.repositories.WishlistRepository;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class BDDTestConfig {
    @Bean
    @Primary
    public WishlistRepository wishlistRepositoryMock() {
        return Mockito.mock(WishlistRepository.class);
    }
}