package com.example.server_Ventas.controller;

import com.example.server_Ventas.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/venta")
public class VentaController {

    @Autowired
    IVentaService ventaServi;

    @GetMapping("/traer")
    public ResponseEntity<?> getVentas(){

        if (ventaServi.getVentas().isEmpty()){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentran ventas registradas");
        }
        return ResponseEntity.ok(ventaServi.getVentas());
    }

    @PostMapping("/crear/{id_carrito}")
    public ResponseEntity<?> saveVenta(@PathVariable int id_carrito){
        return ResponseEntity.ok(ventaServi.saveVenta(id_carrito));
    }

    @DeleteMapping("/delete/{id_venta}")
    public ResponseEntity<?> deleteVenta(@PathVariable int id_venta){

        if (ventaServi.findById(id_venta) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra venta con ID " + id_venta);
        }

        ventaServi.deleteVenta(id_venta);

        return ResponseEntity.ok("Venta eliminada correctamente");
    }

    @GetMapping("/find/{id_venta}")
    public ResponseEntity<?> findById(@PathVariable int id_venta){

        if (ventaServi.findById(id_venta) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra venta con ID " + id_venta);
        }
        return ResponseEntity.ok(ventaServi.findById(id_venta));
    }

    @GetMapping("/conocer/datos/{id_venta}")
    public ResponseEntity<?> knowPrecioList(@PathVariable int id_venta){

        if (ventaServi.findById(id_venta) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra venta con ID " + id_venta);
        }
        return ResponseEntity.ok(ventaServi.knowPrecioList(id_venta));
    }

}
