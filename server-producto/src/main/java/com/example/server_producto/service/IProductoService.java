package com.example.server_producto.service;

import com.example.server_producto.model.Producto;

import java.util.List;
import java.util.Optional;

public interface IProductoService {

    public List<Producto> getProductos();

    public void saveProducto(Producto produc);

    public void deleteById(int id);

    public Producto findById(int id);

    public void editProducto(Producto produc);

    public List<Producto> FaltaStock();
}
