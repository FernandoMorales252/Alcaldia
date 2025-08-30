package com.SysGroup.Alcaldia.Modelos;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

//Modelo de la entidad rol//
@Entity
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer    Id;

    @NotBlank(message = "El nombre del rol no puede estar vacío")
    private String     Nombre;

    //Getters y Setters//
    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        this.Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }
    
    
}
