package br.com.vicente.ordem_ms_desafio_btg.service;

import br.com.vicente.ordem_ms_desafio_btg.controller.dto.OrderResponse;
import br.com.vicente.ordem_ms_desafio_btg.entity.OrderEntity;
import br.com.vicente.ordem_ms_desafio_btg.entity.OrderItem;
import br.com.vicente.ordem_ms_desafio_btg.listener.dto.OrderCreatedEvent;
import br.com.vicente.ordem_ms_desafio_btg.repository.OrderRepository;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final MongoTemplate mongoTemplate;


    public OrderService(OrderRepository orderRepository, MongoTemplate mongoTemplate) {
        this.orderRepository = orderRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public Page<OrderResponse> findAllByCustomerID (Long customerID, PageRequest pageRequest){
        Page<OrderEntity> allByCustomerID = orderRepository.findAllByCustomerID(customerID, pageRequest);
        return allByCustomerID.map(OrderResponse::from);
    }

    public void save(OrderCreatedEvent orderCreatedEvent){
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderID(orderCreatedEvent.codigoPedido());
        orderEntity.setCustomerID(orderCreatedEvent.codigoCliente());
        orderEntity.setItems(getItems(orderCreatedEvent));
        orderEntity.setTotal(getTotal(orderCreatedEvent));

        orderRepository.save(orderEntity);
    }

    public BigDecimal findTotalOnOrdersByCustomerID(Long customerID){
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("customerID").is(customerID)),
                Aggregation.group().sum("total").as("total")
        );
        AggregationResults<Document> response = mongoTemplate.aggregate(aggregation, "tb_orders", Document.class);

        return new BigDecimal(response.getUniqueMappedResult().get("total").toString());
    }

    private BigDecimal getTotal(OrderCreatedEvent orderCreatedEvent) {
        return orderCreatedEvent.itens()
                .stream()
                .map(x->x.preco().multiply(BigDecimal.valueOf(x.quantidade())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    private static List<OrderItem> getItems(OrderCreatedEvent orderCreatedEvent) {
        return orderCreatedEvent.itens().stream().map(x -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setPrice(x.preco());
            orderItem.setProduct(x.produto());
            orderItem.setQuantity(x.quantidade());

            return orderItem;
        }).toList();
    }
}
