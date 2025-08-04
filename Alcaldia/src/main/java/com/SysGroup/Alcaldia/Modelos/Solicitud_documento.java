package com.SysGroup.Alcaldia.Modelos;
import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "Solicitud_documento")
public class Solicitud_documento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_solicitud;

    @Temporal(TemporalType.DATE)
    private Date fecha_solicitud;

    @Enumerated(EnumType.STRING)
    private EstadoSolicitud estado;

    @ManyToOne
    @JoinColumn(name = "id_ciudadano")
    private Ciudadano id_ciudadano;

    @ManyToOne
    @JoinColumn(name = "id_tipo_documento")
    private Tipo_documento id_tipo_documento;

    public enum EstadoSolicitud {
        pendiente,
        en_proceso,
        entregado
    }

    // Getters and Setters
    public Integer getId_solicitud() {
        return id_solicitud;
    }
    public void setId_solicitud(Integer id_solicitud) {
        this.id_solicitud = id_solicitud;
    }
    public Date getFecha_solicitud() {
        return fecha_solicitud;
    }
    public void setFecha_solicitud(Date fecha_solicitud) {
        this.fecha_solicitud = fecha_solicitud;
    }
    public EstadoSolicitud getEstado() {
        return estado;
    }
    public void setEstado(EstadoSolicitud estado) {
        this.estado = estado;
    }
    public Ciudadano getId_ciudadano() {
        return id_ciudadano;
    }
    public void setId_ciudadano(Ciudadano id_ciudadano) {
        this.id_ciudadano = id_ciudadano;
    }
    public Tipo_documento getId_tipo_documento() {
        return id_tipo_documento;
    }
    public void setId_tipo_documento(Tipo_documento id_tipo_documento) {
        this.id_tipo_documento = id_tipo_documento;
    }



}
