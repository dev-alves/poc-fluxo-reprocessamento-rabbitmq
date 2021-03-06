package com.example.pocfluxoreprocessamentorabbitmq.domain.repository;

import com.example.pocfluxoreprocessamentorabbitmq.domain.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
