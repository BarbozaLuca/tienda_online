package com.example.server_CarritoDeC.controller;

import com.example.server_CarritoDeC.service.ICarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    ICarritoService carritoService;

    @Value("${server.port}")
    private int serverPort;

    @GetMapping("/traer")
    public ResponseEntity<?> getCarrito(){

        if (carritoService.getCarritos().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentran carritos existentes");
        }

        return ResponseEntity.ok(carritoService.getCarritos());
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearCarrito(){
        return ResponseEntity.ok(carritoService.saveCarrito());
    }

    @PutMapping("/addProduct/{id_carrito}/{id_producto}")
    public ResponseEntity<?> addProducto(@PathVariable int id_carrito, @PathVariable int id_producto){

        if (carritoService.findById(id_carrito) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro carrito con ID " + id_carrito);
        }
        carritoService.addProducto(id_carrito,id_producto);

        return ResponseEntity.ok("Producto agregado correctamente al carrito");
    }

    @DeleteMapping("/delete/producto/{id_carrito}/{id_producto}")
    public ResponseEntity<?> deleteProducto(@PathVariable int id_carrito, @PathVariable int id_producto){

        if (carritoService.findById(id_carrito) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro carrito con ID " + id_carrito);
        }
        carritoService.deleteProducto(id_carrito,id_producto);

        return ResponseEntity.ok("Producto eliminado correctamente del carrito");
    }

    @DeleteMapping("/delete/{id_carrito}")
    public ResponseEntity<?> deleteCarrito(@PathVariable int id_carrito){

        if (carritoService.findById(id_carrito) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro carrito con ID " + id_carrito);
        }
        carritoService.deleteCarrito(id_carrito);

        return ResponseEntity.ok("Carrito de compras eliminado correctamente");
    }

    @GetMapping("/find/{id_carrito}")
    public ResponseEntity<?> findCarrito(@PathVariable int id_carrito){

        if (carritoService.findById(id_carrito) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro carrito con ID " + id_carrito);
        }

        System.out.println("estoy en el puerto: " + serverPort);
        return ResponseEntity.ok(carritoService.findById(id_carrito));
    }
}
