package com.example.server_Ventas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarritoDTO {

    private int id_carrito;
    private double precioTotal;
    private List<Integer> listaProductosID = new ArrayList<>();

}
