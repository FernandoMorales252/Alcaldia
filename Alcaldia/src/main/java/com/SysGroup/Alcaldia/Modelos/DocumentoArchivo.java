package com.SysGroup.Alcaldia.Modelos;

import java.util.*;
import jakarta.persistence.*;

@Entity
@Table(name = "documento_archivo")
public class DocumentoArchivo {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_documento;

    @Column(name = "numero_documento")
    private String numerodocumento;

    @Temporal(TemporalType.DATE)
    private Date fecha_emision;

    @ManyToOne
    @JoinColumn(name = "id_tipo_documento")
    private Tipo_DocumentoArchivo id_tipo_documento;

    @ManyToOne
    @JoinColumn(name = "id_municipio")
    private Municipio id_municipio;

    private String nombre_persona;
    private String detalles;
    private int estado;



    // Getters and Setters
    public Integer getId_documento() {
        return id_documento;
    }
    public void setId_documento(Integer id_documento) {
        this.id_documento = id_documento;
    }
    public String getNumero_documento() {
        return numerodocumento;
    }
    public void setNumero_documento(String numero_documento) {
        this.numerodocumento = numero_documento;
    }
    public Date getFecha_emision() {
        return fecha_emision;
    }
    public void setFecha_emision(Date fecha_emision) {
        this.fecha_emision = fecha_emision;
    }
    public Tipo_DocumentoArchivo getId_tipo_documento() {
        return id_tipo_documento;
    }
    public void setId_tipo_documento(Tipo_DocumentoArchivo id_tipo_documento) {
        this.id_tipo_documento = id_tipo_documento;
    }

    public String getNombre_persona() {
        return nombre_persona;
    }

    public void setNombre_persona(String nombre_persona) {
        this.nombre_persona = nombre_persona;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

}
