package com.example.server_CarritoDeC.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductoDTO {

    private int id;
    private String nombre;
    private String marca;
    private int stock;
    private double precio;

}
