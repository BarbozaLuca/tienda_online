package com.example.server_producto.service;

import com.example.server_producto.model.Producto;
import com.example.server_producto.repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService implements IProductoService{

    @Autowired
    IProductoRepository producRepo;

    @Override
    public List<Producto> getProductos() {
        return producRepo.findAll();
    }

    @Override
    public void saveProducto(Producto produc) {
        producRepo.save(produc);
    }

    @Override
    public void deleteById(int id) {
        producRepo.deleteById(id);
    }

    @Override
    public Producto findById(int id) {
        return producRepo.findById(id).orElse(null);
    }

    @Override
    public void editProducto(Producto produc) {
        this.saveProducto(produc);
    }

    @Override
    public List<Producto> FaltaStock() {

        List<Producto> listFaltaStock = new ArrayList<>();

        for(Producto produc: getProductos()){
            if (produc.getStock() < 5){
                listFaltaStock.add(produc);
            }
        }
        return listFaltaStock;
    }
}
