package br.com.vicente.ordem_ms_desafio_btg.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;

public class OrderItem {
    private String product;
    private Integer quantity;
    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal price;

    public String getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
