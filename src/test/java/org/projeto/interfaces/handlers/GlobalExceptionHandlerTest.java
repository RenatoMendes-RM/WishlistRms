package org.projeto.interfaces.handlers;

import org.junit.jupiter.api.Test;
import org.projeto.domain.exceptions.BusinessException;
import org.projeto.domain.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void handleBusinessException_returnsBadRequest() {
        String msg = "Erro de negócio";
        BusinessException ex = new BusinessException(msg);

        ResponseEntity<String> response = handler.handleBusinessException(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isEqualTo(msg);
    }

    @Test
    void handleNotFoundException_returnsNotFound() {
        String msg = "Não encontrado";
        NotFoundException ex = new NotFoundException(msg);

        ResponseEntity<String> response = handler.handleNotFoundException(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isEqualTo(msg);
    }
}