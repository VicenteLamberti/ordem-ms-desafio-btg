package br.com.vicente.ordem_ms_desafio_btg.controller.dto;

public record PaginationResponse(
        Integer page,
        Integer pageSize,
        Integer totalElements,
        Integer totalPages
) {
}
