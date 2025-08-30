package com.SysGroup.Alcaldia.Modelos;
import jakarta.persistence.*;

//Modelo de la entidad Inventario//
@Entity
@Table(name = "Inventario")
public class Inventario {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_inventario;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private Integer cantidad;

    @Enumerated(EnumType.STRING)
    private EstadoInventario estado;

    @ManyToOne
    @JoinColumn(name = "id_municipio")
    private Municipio id_municipio;

    public enum EstadoInventario {
        activo,
        inactivo
    }

    // Getters and Setters
    public Integer getId_inventario() {
        return id_inventario;
    }
    public void setId_inventario(Integer id_inventario) {
        this.id_inventario = id_inventario;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Integer getCantidad() {
        return cantidad;
    }
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    public EstadoInventario getEstado() {
        return estado;
    }
    public void setEstado(EstadoInventario estado) {
        this.estado = estado;
    }
    public Municipio getId_municipio() {
        return id_municipio;
    }
    public void setId_municipio(Municipio id_municipio) {
        this.id_municipio = id_municipio;
    }

}
