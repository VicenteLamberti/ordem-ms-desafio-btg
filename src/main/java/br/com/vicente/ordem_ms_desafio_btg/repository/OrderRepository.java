package br.com.vicente.ordem_ms_desafio_btg.repository;

import br.com.vicente.ordem_ms_desafio_btg.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<OrderEntity, Long> {
    Page<OrderEntity> findAllByCustomerID(Long customerID, PageRequest pageRequest);
}
