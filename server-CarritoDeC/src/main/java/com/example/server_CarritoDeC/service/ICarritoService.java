package com.example.server_CarritoDeC.service;

import com.example.server_CarritoDeC.model.Carrito;

import java.util.List;

public interface ICarritoService {

    public List<Carrito> getCarritos();

    public Carrito saveCarrito();

    public void addProducto(int id_carrito,int id_producto);

    public void deleteProducto(int id_carrito, int id_producto);

    public void deleteCarrito(int id_carrito);

    public Carrito findById(int id_carrito);
}
