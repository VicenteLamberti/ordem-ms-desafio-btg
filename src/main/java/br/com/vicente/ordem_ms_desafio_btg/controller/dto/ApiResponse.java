package br.com.vicente.ordem_ms_desafio_btg.controller.dto;

import java.util.List;

public record ApiResponse<T>(
    List<T> data,
    PaginationResponse pagination
){
}
