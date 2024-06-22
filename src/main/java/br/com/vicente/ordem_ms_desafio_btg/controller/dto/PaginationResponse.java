package br.com.vicente.ordem_ms_desafio_btg.controller.dto;

import org.springframework.data.domain.Page;

public record PaginationResponse(
        Integer page,
        Integer pageSize,
        Long totalElements,
        Integer totalPages
) {

    public static PaginationResponse from (Page<?> page){
        return new PaginationResponse(
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }
}
