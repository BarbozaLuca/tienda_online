package com.example.server_CarritoDeC.service;

import com.example.server_CarritoDeC.dto.ProductoDTO;
import com.example.server_CarritoDeC.model.Carrito;
import com.example.server_CarritoDeC.repository.ICarritoRepository;
import com.example.server_CarritoDeC.repository.IProductosApi;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarritoService implements ICarritoService{

    @Autowired
    IProductosApi producAPI;

    @Autowired
    ICarritoRepository carritoRepo;

    @Override
    public List<Carrito> getCarritos() {
        return carritoRepo.findAll();
    }

    @Override
    public Carrito saveCarrito() {

        Carrito carr = new Carrito();
        carr.setPrecioTotal(0.0);

        return carritoRepo.save(carr);
    }

    @Override
    @CircuitBreaker(name = "server-producto", fallbackMethod = "fallbackAddProducto")
    @Retry(name = "server-producto")
    public void addProducto(int id_carrito, int id_producto) {

        Carrito carri = this.findById(id_carrito);

        ProductoDTO producDTO = producAPI.getProductoByID(id_producto);

        if (producDTO.getStock() <= 0){
            throw new IllegalStateException("No hay stock del producto " + producDTO.getNombre());
        }

        carri.setPrecioTotal(carri.getPrecioTotal() + producDTO.getPrecio());
        carri.getListaProductosID().add(producDTO.getId());

//        createException();

        carritoRepo.save(carri);

    }

    public void createException(){
        throw new IllegalArgumentException("prueba resilience y circuit breaker");
    }

    public void fallbackAddProducto(int id_carrito, Throwable throwable) {
        System.out.println("⚠️ Circuit breaker activado. No se pudo agregar producto al carrito " + id_carrito);
        System.out.println("Motivo: " + throwable.getMessage());
    }

    @Override
    @CircuitBreaker( name = "server-producto", fallbackMethod = "fallbackDeleteProducto")
    @Retry( name = "server-producto")
    public void deleteProducto(int id_carrito, int id_producto) {

        Carrito carri = this.findById(id_carrito);

        carri.getListaProductosID().removeIf(id -> id == id_producto);

        ProductoDTO producDTO = producAPI.getProductoByID(id_producto);

        carri.setPrecioTotal(carri.getPrecioTotal() - producDTO.getPrecio());

        carritoRepo.save(carri);

    }

    public void fallbackDeleteProducto(int id_carrito, Throwable throwable) {
        System.out.println("⚠️ Circuit breaker activado. No se pudo eliminar producto al carrito " + id_carrito);
        System.out.println("Motivo: " + throwable.getMessage());
    }

    @Override
    public void deleteCarrito(int id_carrito) {

        Carrito carri = this.findById(id_carrito);

        carritoRepo.deleteById(carri.getId_carrito());

    }

    @Override
    public Carrito findById(int id_carrito) {
        return carritoRepo.findById(id_carrito).orElseThrow(()-> new RuntimeException("Carrito no encontrado"));
    }

}
