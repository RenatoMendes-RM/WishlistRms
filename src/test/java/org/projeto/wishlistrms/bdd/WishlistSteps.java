package org.projeto.wishlistrms.bdd;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.projeto.application.services.WishlistService;
import org.projeto.domain.entities.Wishlist;
import org.projeto.domain.repositories.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


public class WishlistSteps {

    private final String customerId = "cliente-teste";
    @Autowired
    private WishlistRepository wishlistRepository;
    @Autowired
    private WishlistService wishlistService;
    private Set<String> produtosMock = new HashSet<>();

    @Dado("que a wishlist do cliente está vazia")
    public void que_a_wishlist_do_cliente_esta_vazia() {
        produtosMock.clear();
        // Simula que não existe wishlist para o cliente
        when(wishlistRepository.findByCustomerId(customerId)).thenReturn(Optional.empty());
        assertTrue(wishlistService.getAllProducts(customerId).isEmpty());
    }

    @Quando("eu adiciono o produto {string} na wishlist do cliente")
    public void eu_adiciono_o_produto_na_wishlist_do_cliente(String produto) {
        // Simula que a wishlist existe com os produtos atuais
        when(wishlistRepository.findByCustomerId(customerId))
                .thenReturn(Optional.of(new Wishlist("id", customerId, new HashSet<>(produtosMock))));
        wishlistService.addProduct(customerId, produto);
        produtosMock.add(produto); // Atualiza o mock localmente
    }

    @Quando("eu removo o produto {string} da wishlist do cliente")
    public void eu_removo_o_produto_da_wishlist_do_cliente(String produto) {
        when(wishlistRepository.findByCustomerId(customerId))
                .thenReturn(Optional.of(new Wishlist("id", customerId, new HashSet<>(produtosMock))));
        wishlistService.removeProduct(customerId, produto);
        produtosMock.remove(produto);
    }

    @Entao("a wishlist do cliente deve indicar que contém o produto {string}")
    public void a_wishlist_do_cliente_deve_indicar_que_contem_o_produto(String produto) {
        when(wishlistRepository.findByCustomerId(customerId))
                .thenReturn(Optional.of(new Wishlist("id", customerId, new HashSet<>(produtosMock))));
        assertTrue(wishlistService.containsProduct(customerId, produto));
    }

    @Entao("a wishlist do cliente deve conter {int} produto")
    public void a_wishlist_do_cliente_deve_conter_item(int quantidade) {
        when(wishlistRepository.findByCustomerId(customerId))
                .thenReturn(Optional.of(new Wishlist("id", customerId, new HashSet<>(produtosMock))));
        assertEquals(quantidade, wishlistService.getAllProducts(customerId).size());
    }

    @E("a wishlist do cliente deve conter {string}")
    public void a_wishlist_do_cliente_deve_conter(String produto) {
        when(wishlistRepository.findByCustomerId(customerId))
                .thenReturn(Optional.of(new Wishlist("id", customerId, new HashSet<>(produtosMock))));
        assertTrue(wishlistService.containsProduct(customerId, produto));
    }

    @E("a wishlist do cliente não deve conter {string}")
    public void a_wishlist_do_cliente_nao_deve_conter(String produto) {
        when(wishlistRepository.findByCustomerId(customerId))
                .thenReturn(Optional.of(new Wishlist("id", customerId, new HashSet<>(produtosMock))));
        assertFalse(wishlistService.containsProduct(customerId, produto));
    }

    @E("a wishlist do cliente deve conter {int} produtos")
    public void aWishlistDoClienteDeveConterProdutos(int quantidade) {
        when(wishlistRepository.findByCustomerId(customerId))
                .thenReturn(Optional.of(new Wishlist("id", customerId, new HashSet<>(produtosMock))));
        assertEquals(quantidade, wishlistService.getAllProducts(customerId).size());
    }
}