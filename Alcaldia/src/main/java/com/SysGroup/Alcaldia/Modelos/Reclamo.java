package com.SysGroup.Alcaldia.Modelos;

import java.util.*;
import jakarta.persistence.*;

@Entity
@Table(name = "Reclamo")
public class Reclamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_reclamo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Temporal(TemporalType.DATE)
    private Date fecha_reclamo;

    @Enumerated(EnumType.STRING)
    private EstadoReclamo estado;

    @ManyToOne
    @JoinColumn(name = "id_ciudadano")
    private Ciudadano id_ciudadano;

    public enum EstadoReclamo {
        pendiente,
        en_proceso,
        resuelto
    }

    // Getters and Setters
    public Integer getId_reclamo() {
        return id_reclamo;
    }
    public void setId_reclamo(Integer id_reclamo) {
        this.id_reclamo = id_reclamo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Date getFecha_reclamo() {
        return fecha_reclamo;
    }
    

}
