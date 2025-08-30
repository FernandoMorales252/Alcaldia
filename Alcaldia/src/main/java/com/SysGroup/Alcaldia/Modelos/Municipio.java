package com.SysGroup.Alcaldia.Modelos;
import jakarta.persistence.*;
 
//Modelo de la entidad municipio//
@Entity
@Table(name = "municipio")
public class Municipio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_municipio;

    private String nombre_municipio;

    //Getters y Setters//

    public Integer getId_municipio() {
        return id_municipio;
    }

    public void setId_municipio(Integer id_municipio) {
        this.id_municipio = id_municipio;
    }

    public String getNombre_municipio() {
        return nombre_municipio;
    }

    public void setNombre_municipio(String nombre_municipio) {
        this.nombre_municipio = nombre_municipio;
    }
}