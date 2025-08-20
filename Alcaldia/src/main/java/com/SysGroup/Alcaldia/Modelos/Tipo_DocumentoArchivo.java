package com.SysGroup.Alcaldia.Modelos;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.*;

@Entity
@Table(name = "tipodocumentoarchivo")
public class Tipo_DocumentoArchivo {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id_tipo_documento;
    
    private String nombre_tipo;
    

    public Integer getId_tipo_documento() {
        return id_tipo_documento;
    }
    public void setId_tipo_documento(Integer id_tipo_documento) {
        this.id_tipo_documento = id_tipo_documento;
    }

    public String getNombre_tipo() {
        return nombre_tipo;
    }
    public void setNombre_tipo(String nombre_tipo) {
        this.nombre_tipo = nombre_tipo;
    }


}

