package com.example.server_Ventas.repository;

import com.example.server_Ventas.dto.ProductoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "server-producto")
public interface IProductoAPI {

    @GetMapping("/producto/traerUno/{id}")
    public ProductoDTO getProductodById(@PathVariable int id);

    @PutMapping("/producto/editar")
    public void editProducto(@RequestBody ProductoDTO produc);

}
