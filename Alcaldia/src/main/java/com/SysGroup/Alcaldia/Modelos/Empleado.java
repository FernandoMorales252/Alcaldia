package com.SysGroup.Alcaldia.Modelos;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "empleado")
public class Empleado {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_empleado;
@NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
    @NotBlank(message = "El apellido no puede estar vacío")
    private String apellido;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "La fecha de contratación no puede estar vacía")
    private LocalDate  fecha_contratacion;

    @NotNull(message = "El estado no puede estar vacío")
    private int estado;

    @ManyToOne
    @JoinColumn(name = "id_cargo")
    @NotNull(message = "El cargo no puede estar vacío")
    private Cargo cargo;

    @ManyToOne
    @JoinColumn(name = "id_municipio")
    @NotNull(message = "El municipio no puede estar vacío")
    private Municipio municipio;

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
    public LocalDate getFecha_contratacion() {
        return fecha_contratacion;
    }
    public void setFecha_contratacion(LocalDate fecha_contratacion) {
        this.fecha_contratacion = fecha_contratacion;
    }
    public int getEstado() {
        return estado;
    }
    public void setEstado(int estado) {
        this.estado = estado;
    }
    public Cargo getCargo() {
        return cargo;
    }
    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
    public Municipio getMunicipio() {
        return municipio;
    }
    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }
}
