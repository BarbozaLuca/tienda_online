package com.example.server_CarritoDeC.model;

import com.example.server_CarritoDeC.dto.ProductoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id_carrito;
    private double precioTotal;
    @ElementCollection
    private List<Integer> listaProductosID = new ArrayList<>();

}
