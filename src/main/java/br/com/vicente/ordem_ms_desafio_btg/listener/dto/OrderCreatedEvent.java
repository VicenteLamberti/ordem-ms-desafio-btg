package br.com.vicente.ordem_ms_desafio_btg.listener.dto;

import java.util.List;

public record OrderCreatedEvent(
        Long codigoPedido,
        Long codigoCliente,
        List<OrderItemEvent>itens
) {
}
