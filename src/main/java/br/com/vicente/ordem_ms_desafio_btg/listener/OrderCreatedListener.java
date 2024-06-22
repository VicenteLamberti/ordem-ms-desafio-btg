package br.com.vicente.ordem_ms_desafio_btg.listener;

import br.com.vicente.ordem_ms_desafio_btg.listener.dto.OrderCreatedEvent;
import br.com.vicente.ordem_ms_desafio_btg.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import static br.com.vicente.ordem_ms_desafio_btg.configuration.RabbitMqConfiguration.ORDER_CREATED_QUEUE;
@Component
public class OrderCreatedListener {

    private final Logger logger = LoggerFactory.getLogger(OrderCreatedListener.class);

    private final OrderService orderService;

    public OrderCreatedListener(OrderService orderService) {
        this.orderService = orderService;
    }

    @RabbitListener(queues = ORDER_CREATED_QUEUE)
    public void listen(Message<OrderCreatedEvent> message){
        logger.info("message consumed {}", message);

        orderService.save(message.getPayload());

    }
}
