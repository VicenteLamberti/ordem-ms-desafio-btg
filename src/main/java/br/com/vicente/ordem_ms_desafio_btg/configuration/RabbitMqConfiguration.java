package br.com.vicente.ordem_ms_desafio_btg.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfiguration {
    public static final String ORDER_CREATED_QUEUE = "btg-pactual-order-created";
}
