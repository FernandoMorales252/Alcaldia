package com.SysGroup.Alcaldia.Modelos;
import jakarta.persistence.*;
 

@Entity
@Table(name = "Municipio")
public class Municipio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer id_municipio;

    private String nombre_municipio;

}
