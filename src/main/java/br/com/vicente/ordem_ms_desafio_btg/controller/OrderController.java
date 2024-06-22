package br.com.vicente.ordem_ms_desafio_btg.controller;

import br.com.vicente.ordem_ms_desafio_btg.controller.dto.ApiResponse;
import br.com.vicente.ordem_ms_desafio_btg.controller.dto.OrderResponse;
import br.com.vicente.ordem_ms_desafio_btg.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/customers/{customerID}/orders")
    public ResponseEntity<ApiResponse<OrderResponse>> listOrders(
            @RequestParam(name = "page", defaultValue = "10") Integer page,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
    ){

        return ResponseEntity.ok(null);
    }
}
