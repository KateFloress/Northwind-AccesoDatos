package model;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Producto {
    private Long id;
    private String nombre;
    private String descripcion;
    private int cantidad;
    private double precio;

    public Producto(String nombre, String descripcion, int cantidad, double precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
    }
}
