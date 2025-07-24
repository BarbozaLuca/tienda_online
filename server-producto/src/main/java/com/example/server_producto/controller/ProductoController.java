package com.example.server_producto.controller;

import com.example.server_producto.model.Producto;
import com.example.server_producto.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    IProductoService producServi;

    @Value("${server.port}")
    private int serverPort;

    @GetMapping("/traer")
    public ResponseEntity<?> getProductos(){

        if (producServi.getProductos().isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentran productos cargados");
        }
        return ResponseEntity.ok(producServi.getProductos());
    }

    @PostMapping("/crear")
    public ResponseEntity<String> saveProduc(@RequestBody Producto produc){

        if (produc == null || produc.getNombre()==null || produc.getPrecio() < 0){
            return ResponseEntity.badRequest().body("Los datos del producto son invalidos");
        }

        producServi.saveProducto(produc);
        return ResponseEntity.status(HttpStatus.CREATED).body("Producto creado correctamente");

    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> deleteProduc(@PathVariable int id){

        if (producServi.findById(id) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro un producto con ID: " + id);
        }

        producServi.deleteById(id);
        return ResponseEntity.ok("Producto eliminado correctamente");
    }

    @GetMapping("/traerUno/{id}")
    public ResponseEntity<?> findById(@PathVariable int id){

//        Para probar balanceo de cargar
//        System.out.println("estoy en el puerto: " + serverPort);
           if (producServi.findById(id) == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro un producto con ID: " + id);
           }
           return ResponseEntity.ok(producServi.findById(id));
    }

    @GetMapping("/falta_stock")
    public ResponseEntity<?> getFaltaStock() {

        if (producServi.FaltaStock().isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay productos con falta de stock");
        }

        return ResponseEntity.ok(producServi.FaltaStock());
    }

    @PutMapping("/editar")
    public ResponseEntity<String> editProducto(@RequestBody Producto produc) {

        if (producServi.findById(produc.getId()) != null){

            producServi.editProducto(produc);

            return ResponseEntity.ok("Producto modificado correctamente");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el producto con ID " + produc.getId());
    }
}
