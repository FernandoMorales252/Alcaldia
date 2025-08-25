package com.SysGroup.Alcaldia.Modelos;

import java.util.*;
import jakarta.persistence.*;


@Entity
@Table(name = "documento_archivo")
public class DocumentoArchivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDocumento;

    @Column(name = "numero_documento")
    private String numeroDocumento;

    @Temporal(TemporalType.DATE)
    private Date fechaEmision;

    @ManyToOne
    @JoinColumn(name = "id_tipo_documento")
    private Tipo_DocumentoArchivo tipoDocumento;

    @ManyToOne
    @JoinColumn(name = "id_municipio")
    private Municipio municipio;

    private String nombrePersona;
    private String detalles;

    private int estado;

    // Getters & Setters
    public Integer getIdDocumento() { return idDocumento; }
    public void setIdDocumento(Integer idDocumento) { this.idDocumento = idDocumento; }

    public String getNumeroDocumento() { return numeroDocumento; }
    public void setNumeroDocumento(String numeroDocumento) { this.numeroDocumento = numeroDocumento; }

    public Date getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(Date fechaEmision) { this.fechaEmision = fechaEmision; }

    public Tipo_DocumentoArchivo getTipoDocumento() { return tipoDocumento; }
    public void setTipoDocumento(Tipo_DocumentoArchivo tipoDocumento) { this.tipoDocumento = tipoDocumento; }

    public Municipio getMunicipio() { return municipio; }
    public void setMunicipio(Municipio municipio) { this.municipio = municipio; }

    public String getNombrePersona() { return nombrePersona; }
    public void setNombrePersona(String nombrePersona) { this.nombrePersona = nombrePersona; }

    public String getDetalles() { return detalles; }
    public void setDetalles(String detalles) { this.detalles = detalles; }

    public int getEstado() { return estado; }
    public void setEstado(int estado) { this.estado = estado; }
}


