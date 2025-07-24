package com.example.server_CarritoDeC.repository;

import com.example.server_CarritoDeC.model.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICarritoRepository extends JpaRepository<Carrito,Integer> {


}
