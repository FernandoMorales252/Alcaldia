package com.SysGroup.Alcaldia.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.SysGroup.Alcaldia.Modelos.Inventario;

public interface IInventarioRepository extends JpaRepository<Inventario, Integer> {

}

