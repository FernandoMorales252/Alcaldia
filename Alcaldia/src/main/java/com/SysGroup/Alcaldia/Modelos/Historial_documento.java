package com.SysGroup.Alcaldia.Modelos;
import java.util.*;
import jakarta.persistence.*;

@Entity
@Table(name = "Historial_documento")
public class Historial_documento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_historial;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "id_solicitud")
    private Solicitud_documento id_solicitud;

    @ManyToOne
    @JoinColumn(name = "aprobado_por")
    private Usuario aprobado_por;

    // Getters and Setters
    public Integer getId_historial() {
        return id_historial;
    }
    public void setId_historial(Integer id_historial) {
        this.id_historial = id_historial;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public Solicitud_documento getId_solicitud() {
        return id_solicitud;
    }
    public void setId_solicitud(Solicitud_documento id_solicitud) {
        this.id_solicitud = id_solicitud;
    }
    public Usuario getAprobado_por() {
        return aprobado_por;
    }
    public void setAprobado_por(Usuario aprobado_por) {
        this.aprobado_por = aprobado_por;
    }
    

}
