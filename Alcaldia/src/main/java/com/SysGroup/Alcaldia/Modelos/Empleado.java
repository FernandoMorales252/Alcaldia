package com.SysGroup.Alcaldia.Modelos;
import jakarta.persistence.*;


@Entity
@Table(name = "Empleado")
public class Empleado {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_empleado;

    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String correo_electronico;

    @Enumerated(EnumType.STRING)
    private EstadoEmpleado estado;

    @ManyToOne
    @JoinColumn(name = "id_cargo")
    private Cargo id_cargo;

    @ManyToOne
    @JoinColumn(name = "id_municipio")
    private Municipio id_municipio;

    public enum EstadoEmpleado {
        activo,
        inactivo
    }

// Getters and Setters
    public Integer getId_empleado() {
        return id_empleado;
    }
    public void setId_empleado(Integer id_empleado) {
        this.id_empleado = id_empleado;
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
    public EstadoEmpleado getEstado() {
        return estado;
    }
    public void setEstado(EstadoEmpleado estado) {
        this.estado = estado;
    }
    public Cargo getId_cargo() {
        return id_cargo;
    }
    public void setId_cargo(Cargo id_cargo) {
        this.id_cargo = id_cargo;
    }
    public Municipio getId_municipio() {
        return id_municipio;
    }
    public void setId_municipio(Municipio id_municipio) {
        this.id_municipio = id_municipio;
    }


}
