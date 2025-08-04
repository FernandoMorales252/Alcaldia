package com.SysGroup.Alcaldia.Modelos;

import java.math.*;
import java.util.*;
import jakarta.persistence.*;

@Entity
@Table(name = "Proyecto")
public class Proyecto {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_proyecto;

    private String nombre_proyecto;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Temporal(TemporalType.DATE)
    private Date fecha_inicio;

    @Temporal(TemporalType.DATE)
    private Date fecha_fin;

    @Enumerated(EnumType.STRING)
    private EstadoProyecto estado;

    private BigDecimal presupuesto;

    private String imagen_url;

    @ManyToOne
    @JoinColumn(name = "id_municipio")
    private Municipio id_municipio;

    public enum EstadoProyecto {
        activo,
        inactivo
    }

    // Getters and Setters
    public Integer getId_proyecto() {
        return id_proyecto;
    }
    public void setId_proyecto(Integer id_proyecto) {
        this.id_proyecto = id_proyecto;
    }
    public String getNombre_proyecto() {
        return nombre_proyecto;
    }
    public void setNombre_proyecto(String nombre_proyecto) {
        this.nombre_proyecto = nombre_proyecto;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Date getFecha_inicio() {
        return fecha_inicio;
    }
    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }
    public Date getFecha_fin() {
        return fecha_fin;
    }
    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }
    public EstadoProyecto getEstado() {
        return estado;
    }
    public void setEstado(EstadoProyecto estado) {
        this.estado = estado;
    }
    public BigDecimal getPresupuesto() {
        return presupuesto;
    }
    public void setPresupuesto(BigDecimal presupuesto) {
        this.presupuesto = presupuesto;
    }
    public String getImagen_url() {
        return imagen_url;
    }
    public void setImagen_url(String imagen_url) {
        this.imagen_url = imagen_url;
    }
    public Municipio getId_municipio() {
        return id_municipio;
    }
    public void setId_municipio(Municipio id_municipio) {
        this.id_municipio = id_municipio;
    }
    

}
