package org.projeto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class WishListAppTest {

    @Test
    void main_runsWithoutExceptions() {
        assertDoesNotThrow(() -> WishListApp.main(new String[]{}));
    }
}