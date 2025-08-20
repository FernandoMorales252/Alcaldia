package com.SysGroup.Alcaldia.Modelos;
import jakarta.persistence.*;

@Entity
@Table(name = "Usuario")
public class Usuario {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_login;

    @Column(unique = true, nullable = false)
    private String usuario;

    private String contrasena;

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
    
    

}
