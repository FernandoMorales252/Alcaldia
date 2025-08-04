package com.SysGroup.Alcaldia.Modelos;
import jakarta.persistence.*;

@Entity
@Table(name = "Login_ciudadano")
public class Login_ciudadano {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_login;

    @Column(unique = true, nullable = false)
    private String usuario;

    private String contrasena;

    @ManyToOne
    @JoinColumn(name = "id_ciudadano")
    private Ciudadano id_ciudadano;

    // Getters and Setters
    public Integer getId_login() {
        return id_login;
    }
    public void setId_login(Integer id_login) {
        this.id_login = id_login;
    }
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    public Ciudadano getId_ciudadano() {
        return id_ciudadano;
    }
    public void setId_ciudadano(Ciudadano id_ciudadano) {
        this.id_ciudadano = id_ciudadano;
    }


}
