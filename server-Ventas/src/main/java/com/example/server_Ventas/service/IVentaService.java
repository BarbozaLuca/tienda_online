package com.example.server_Ventas.service;

import com.example.server_Ventas.dto.VentaDTO;
import com.example.server_Ventas.model.Venta;

import java.util.List;

public interface IVentaService {

    public List<Venta> getVentas();

    public Venta saveVenta(int id_Carrito);

    public void deleteVenta(int id_Venta);

    public Venta findById(int id_Venta);

    public VentaDTO knowPrecioList(int id_Venta);
}