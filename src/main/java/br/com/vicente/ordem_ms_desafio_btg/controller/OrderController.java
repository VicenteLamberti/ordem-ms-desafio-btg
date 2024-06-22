package br.com.vicente.ordem_ms_desafio_btg.controller;

import br.com.vicente.ordem_ms_desafio_btg.controller.dto.ApiResponse;
import br.com.vicente.ordem_ms_desafio_btg.controller.dto.OrderResponse;
import br.com.vicente.ordem_ms_desafio_btg.controller.dto.PaginationResponse;
import br.com.vicente.ordem_ms_desafio_btg.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/customers/{customerID}/orders")
    public ResponseEntity<ApiResponse<OrderResponse>> listOrders(
            @PathVariable(name = "customerID") Long customerID,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
    ){

        Page<OrderResponse> responses = orderService.findAllByCustomerID(customerID, PageRequest.of(page, pageSize));
        BigDecimal totalOnOrdersByCustomerID = orderService.findTotalOnOrdersByCustomerID(customerID);
        return ResponseEntity.ok(new ApiResponse<>(
                Map.of("total", totalOnOrdersByCustomerID),
                responses.getContent(),
                PaginationResponse.from(responses)
        ));
    }
}
