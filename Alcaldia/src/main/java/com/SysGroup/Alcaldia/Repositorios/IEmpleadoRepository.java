package com.SysGroup.Alcaldia.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SysGroup.Alcaldia.Modelos.Empleado;

public interface IEmpleadoRepository extends JpaRepository<Empleado, Integer> {

}
