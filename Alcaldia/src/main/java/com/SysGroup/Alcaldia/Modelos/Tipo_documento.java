package com.SysGroup.Alcaldia.Modelos;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class Tipo_documento {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id_Tipo_documento;
    
    private String nombre_Tipo_documento;
    private String descripcion;

    public Integer getId_Tipo_documento() {
        return id_Tipo_documento;
    }

    public void setId_Tipo_documento(Integer id_Tipo_documento) {
        this.id_Tipo_documento = id_Tipo_documento;
    }

    public String getNombre_Tipo_documento() {
        return nombre_Tipo_documento;
    }

    public void setNombre_Tipo_documento(String nombre_Tipo_documento) {
        this.nombre_Tipo_documento = nombre_Tipo_documento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

