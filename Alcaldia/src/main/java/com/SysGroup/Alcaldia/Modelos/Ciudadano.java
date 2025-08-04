package com.SysGroup.Alcaldia.Modelos;
import jakarta.persistence.*;

@Entity
@Table(name = "Ciudadano")
public class Ciudadano {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_ciudadano;

    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String correo_electronico;
    private String numero_identificacion;

    @ManyToOne
    @JoinColumn(name = "id_municipio")
    private Municipio id_municipio;

    // Getters and Setters
    public Integer getId_ciudadano() {
        return id_ciudadano;
    }
    public void setId_ciudadano(Integer id_ciudadano) {
        this.id_ciudadano = id_ciudadano;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getCorreo_electronico() {
        return correo_electronico;
    }
    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }
    public String getNumero_identificacion() {
        return numero_identificacion;
    }
    public void setNumero_identificacion(String numero_identificacion) {
        this.numero_identificacion = numero_identificacion;
    }

    public Municipio getId_municipio() {
        return id_municipio;
    }
    public void setId_municipio(Municipio id_municipio) {
        this.id_municipio = id_municipio;}


}
