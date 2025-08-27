package com.SysGroup.Alcaldia.Repositorios;
import com.SysGroup.Alcaldia.Modelos.Usuario;
import org.springframework.data.jpa.repository.*;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
  

}
