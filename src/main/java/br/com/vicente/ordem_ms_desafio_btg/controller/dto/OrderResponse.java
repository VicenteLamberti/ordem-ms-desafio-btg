package br.com.vicente.ordem_ms_desafio_btg.controller.dto;

import br.com.vicente.ordem_ms_desafio_btg.entity.OrderEntity;

import java.math.BigDecimal;

public record OrderResponse(
        Long orderID,
        Long customerID,
        BigDecimal total
) {

    public static OrderResponse from(OrderEntity orderEntity){
        return new OrderResponse(
                orderEntity.getOrderID(),
                orderEntity.getCustomerID(),
                orderEntity.getTotal()
        );
    }
}
