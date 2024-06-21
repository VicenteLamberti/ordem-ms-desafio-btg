package br.com.vicente.ordem_ms_desafio_btg.entity;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;
import java.util.List;

@Document(collection = "tb_orders")
public class OrderEntity {
    @MongoId
    private Long orderID;
    @Indexed(name = "customer_id_index")
    private Long customerID;
    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal total;
    private List<OrderItem> items;

    public Long getOrderID() {
        return orderID;
    }

    public Long getCustomerID() {
        return customerID;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public List<OrderItem> getItems() {
        return items;
    }
}
