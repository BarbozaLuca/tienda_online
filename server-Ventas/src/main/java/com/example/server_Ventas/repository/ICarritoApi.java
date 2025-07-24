package com.example.server_Ventas.repository;

import com.example.server_Ventas.dto.CarritoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient( name = "server-CarritoDeC")
public interface ICarritoApi {

    @GetMapping("/carrito/find/{id_carrito}")
    public CarritoDTO getCarritoById(@PathVariable("id_carrito") int id_carrito);

    @DeleteMapping("/carrito/delete/{id_carrito}")
    public void deleteCarrito(@PathVariable int id_carrito);

}
