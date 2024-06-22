package br.com.vicente.ordem_ms_desafio_btg.controller.dto;

import java.math.BigDecimal;

public record OrderResponse(
        Long orderID,
        Long customerID,
        BigDecimal total
) {
}
