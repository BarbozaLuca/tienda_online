package com.example.server_Ventas.service;

import com.example.server_Ventas.dto.CarritoDTO;
import com.example.server_Ventas.dto.ProductoDTO;
import com.example.server_Ventas.dto.VentaDTO;
import com.example.server_Ventas.model.Venta;
import com.example.server_Ventas.repository.ICarritoApi;
import com.example.server_Ventas.repository.IProductoAPI;
import com.example.server_Ventas.repository.IVentaRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VentaService implements IVentaService{

    @Autowired
    IVentaRepository ventaRepo;

    @Autowired
    ICarritoApi carritoApi;

    @Autowired
    IProductoAPI productoApi;

    @Override
    public List<Venta> getVentas() {
        return ventaRepo.findAll();
    }

    @Override
    @CircuitBreaker(name = "server-CarritoDeC", fallbackMethod = "fallbackSaveVenta")
    @Retry(name = "server-CarritoDeC")
    public Venta saveVenta(int id_Carrito) {

        CarritoDTO carrito = carritoApi.getCarritoById(id_Carrito);

        for (int index : carrito.getListaProductosID() ){

            ProductoDTO producto = productoApi.getProductodById(index);

            producto.setStock(producto.getStock()-1);
            productoApi.editProducto(producto);

        }

        Venta ventaNueva = new Venta();
        ventaNueva.setFecha_Venta(LocalDate.now());
        ventaNueva.setId_Carrito(carrito.getId_carrito());

        carritoApi.deleteCarrito(id_Carrito);

//        Para probar que funcione el CircuitBreaker
//        createException();

        return ventaRepo.save(ventaNueva);
    }

    public void createException(){
        throw new IllegalArgumentException("prueba resilience y circuit breaker");
    }

    public Venta fallbackSaveVenta(Throwable throwable){
        return new Venta(999999,null,999999);
    }

    @Override
    public void deleteVenta(int id_Venta) {

        ventaRepo.deleteById(id_Venta);

    }

    @Override
    public Venta findById(int id_Venta) {
        return ventaRepo.findById(id_Venta).orElseThrow(() -> new RuntimeException("Venta no encontada"));
    }

    @Override
    @CircuitBreaker( name = "server-CarritoDeC", fallbackMethod = "fallbackKnowPrecioList")
    @Retry(name = "server-CarritoDeC")
    public VentaDTO knowPrecioList(int id_Venta) {

        VentaDTO ventaDTO = new VentaDTO();
        Venta venta = this.findById(id_Venta);
        CarritoDTO carrito = carritoApi.getCarritoById(venta.getId_Carrito());

        ventaDTO.setId_venta(venta.getId_Venta());
        ventaDTO.setId_carrito(venta.getId_Carrito());
        ventaDTO.setPrecioTotal(carrito.getPrecioTotal());
        ventaDTO.setListaProductosID(carrito.getListaProductosID());

//        Para probar que funcione el CircuitBreaker
//        createException();

        return ventaDTO;
    }

    public VentaDTO fallbackKnowPrecioList(Throwable throwable){
        return new VentaDTO(999999,999999,999999,null);
    }
}
