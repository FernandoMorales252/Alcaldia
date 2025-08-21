package com.SysGroup.Alcaldia.Repositorios;
import com.sysGroup.Alcaldia.Modelos.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MunicipioRepository extends JpaRepository<Municipio, Integer> {
    // Additional query methods can be defined here if needed

}
