package br.com.vicente.ordem_ms_desafio_btg.listener.dto;

import java.math.BigDecimal;

public record OrderItemEvent(
        String produto,
        Integer quantidade,
        BigDecimal preco

) {
}
