package io.github.arch2be.orderapprovalservice.framework.out.db;

import io.github.arch2be.orderapprovalservice.application.domain.model.Product;
import io.github.arch2be.orderapprovalservice.application.domain.model.ProductType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.springframework.data.annotation.PersistenceCreator;

@Entity(name = "product")
class ProductEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private ProductType type;
    private String details;

    @PersistenceCreator
    public ProductEntity() {
    }

    ProductEntity(final ProductType type, final String details) {
        this.type = type;
        this.details = details;
    }

    Product toDomain() {
        return new Product(type, details);
    }
}
