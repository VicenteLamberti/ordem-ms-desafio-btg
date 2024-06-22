package br.com.vicente.ordem_ms_desafio_btg.repository;

import br.com.vicente.ordem_ms_desafio_btg.entity.OrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<OrderEntity, Long> {
}
