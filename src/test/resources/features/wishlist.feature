Feature: Gerenciamento de Wishlist

  Scenario: Adicionar dois produtos e remover um deles da wishlist
    Given que a wishlist do cliente está vazia
    When eu adiciono o produto "Livro Clean Architecture" na wishlist do cliente
    And eu adiciono o produto "Livro Effective Java" na wishlist do cliente
    Then a wishlist do cliente deve indicar que contém o produto "Livro Clean Architecture"
    And a wishlist do cliente deve indicar que contém o produto "Livro Effective Java"
    And a wishlist do cliente deve conter 2 produtos
    When eu removo o produto "Livro Clean Architecture" da wishlist do cliente
    Then a wishlist do cliente deve conter 1 produto
    And a wishlist do cliente deve conter "Livro Effective Java"
    And a wishlist do cliente não deve conter "Livro Clean Architecture"
