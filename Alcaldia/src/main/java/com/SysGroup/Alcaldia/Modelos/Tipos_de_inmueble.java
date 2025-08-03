package com.SysGroup.Alcaldia.Modelos;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class Tipos_de_inmueble {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id_tipo_inmueble;
    
    private String nombre_tipo_inmueble;
    private String descripcion;

    public Integer getId_tipo_inmueble() {
        return id_tipo_inmueble;
    }

    public String getNombre_tipo_inmueble() {
        return nombre_tipo_inmueble;
    }
    public String getDescripcion() {
        return descripcion;
    }
    


}
