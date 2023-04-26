package com.cibertec.propuestosdawi.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @Column(name = "id_producto")
    private String idProducto;
    private String descripcion;
    private int stock;
    private double precio;

    @Column(name = "id_categoria")
    private int idCategoria;

    @OneToOne
    @JoinColumn(name = "id_categoria",updatable = false,insertable = false)
    private Categoria categoria;

    @Column(name = "id_proveedor")
    private int idProveedor;

    @OneToOne
    @JoinColumn(name = "id_proveedor",updatable = false,insertable = false)
    private Proveedor proveedor;

    private String estado;
}
