package org.projeto.wishlistrms.bdd;

import io.cucumber.java.en.And;
import io.cucumber.java.pt.*;
import org.projeto.application.services.WishlistService;
import org.projeto.domain.repositories.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
        import java.util.*;

public class WishlistSteps {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private WishlistService wishlistService;

    private final String customerId = "cliente-teste"; // Use um ID fixo para o teste

    @Dado("que a wishlist do cliente está vazia")
    public void que_a_wishlist_do_cliente_esta_vazia() {
        wishlistRepository.deleteByCustomerId(customerId);
        assertTrue(wishlistService.getAllProducts(customerId).isEmpty());
    }

    @Quando("eu adiciono o produto {string} na wishlist do cliente")
    public void eu_adiciono_o_produto_na_wishlist_do_cliente(String produto) {
        wishlistService.addProduct(customerId, produto);
    }

    @Entao("a wishlist do cliente deve indicar que contém o produto {string}")
    public void a_wishlist_do_cliente_deve_indicar_que_contem_o_produto(String produto) {
        assertTrue(wishlistService.containsProduct(customerId, produto));
    }

    @Quando("eu removo o produto {string} da wishlist do cliente")
    public void eu_removo_o_produto_da_wishlist_do_cliente(String produto) {
        wishlistService.removeProduct(customerId, produto);
    }

    @Entao("a wishlist do cliente deve conter {int} produto")
    public void a_wishlist_do_cliente_deve_conter_item(int quantidade) {
        assertEquals(quantidade, wishlistService.getAllProducts(customerId).size());
    }

    @E("a wishlist do cliente deve conter {string}")
    public void a_wishlist_do_cliente_deve_conter(String produto) {
        assertTrue(wishlistService.containsProduct(customerId, produto));
    }

    @E("a wishlist do cliente não deve conter {string}")
    public void a_wishlist_do_cliente_nao_deve_conter(String produto) {
        assertFalse(wishlistService.containsProduct(customerId, produto));
    }

    @And("a wishlist do cliente deve conter {int} produtos")
    public void aWishlistDoClienteDeveConterProdutos(int quantidade) {
        assertEquals(quantidade, wishlistService.getAllProducts(customerId).size());
    }
}