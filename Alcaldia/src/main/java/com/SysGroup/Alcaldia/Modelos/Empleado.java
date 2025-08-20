package com.SysGroup.Alcaldia.Modelos;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "empleado")
public class Empleado {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_empleado;

    private String nombre;
    private String apellido;
    private Date fecha_contratacion;
    private int estado;

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
    public Date getFecha_contratacion() {
        return fecha_contratacion;
    }
    public void setFecha_contratacion(Date fecha_contratacion) {
        this.fecha_contratacion = fecha_contratacion;
    }
    public int getEstado() {
        return estado;
    }
    public void setEstado(int estado) {
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
