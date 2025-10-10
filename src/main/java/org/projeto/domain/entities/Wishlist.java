package org.projeto.domain.entities;

import java.util.HashSet;
import java.util.Set;

public class Wishlist {
    private String id;
    private String customerId;
    private Set<String> productIds = new HashSet<>();

    public Wishlist(String id, String customerId, Set<String> productIds) {
        this.id = id;
        this.customerId = customerId;
        this.productIds = productIds;
    }

    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Set<String> getProductIds() {
        return productIds;
    }
}