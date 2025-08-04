package com.SysGroup.Alcaldia.Modelos;

import java.util.*;
import jakarta.persistence.*;

@Entity
@Table(name = "Documento")
public class Documento {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_documento;

    private String numero_documento;

    @Temporal(TemporalType.DATE)
    private Date fecha_emision;

    @ManyToOne
    @JoinColumn(name = "id_tipo_documento")
    private Tipo_documento id_tipo_documento;

    @ManyToOne
    @JoinColumn(name = "id_ciudadano")
    private Ciudadano id_ciudadano;

    // Getters and Setters
    public Integer getId_documento() {
        return id_documento;
    }
    public void setId_documento(Integer id_documento) {
        this.id_documento = id_documento;
    }
    public String getNumero_documento() {
        return numero_documento;
    }
    public void setNumero_documento(String numero_documento) {
        this.numero_documento = numero_documento;
    }
    public Date getFecha_emision() {
        return fecha_emision;
    }
    public void setFecha_emision(Date fecha_emision) {
        this.fecha_emision = fecha_emision;
    }
    public Tipo_documento getId_tipo_documento() {
        return id_tipo_documento;
    }
    public void setId_tipo_documento(Tipo_documento id_tipo_documento) {
        this.id_tipo_documento = id_tipo_documento;
    }
    public Ciudadano getId_ciudadano() {
        return id_ciudadano;
    }
    public void setId_ciudadano(Ciudadano id_ciudadano) {
        this.id_ciudadano = id_ciudadano;
    }
    

}
