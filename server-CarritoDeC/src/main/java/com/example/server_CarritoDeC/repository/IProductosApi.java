package com.example.server_CarritoDeC.repository;

import com.example.server_CarritoDeC.dto.ProductoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "server-producto")
public interface IProductosApi {


    @GetMapping("/producto/traerUno/{producto_id}")
    public ProductoDTO getProductoByID(@PathVariable("producto_id") int producto_id);

}
