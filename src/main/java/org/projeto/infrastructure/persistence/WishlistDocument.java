package org.projeto.infrastructure.persistence;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Set;
import org.projeto.domain.entities.Wishlist;

@Document(collection = "wishlists")
public class WishlistDocument {
    @Id
    private String id;
    private String customerId;
    private Set<String> productIds;

    public WishlistDocument() {
    }

    public WishlistDocument(String id, String customerId, Set<String> productIds) {
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

    public void setId(String id) {
        this.id = id;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setProductIds(Set<String> productIds) {
        this.productIds = productIds;
    }

}