package com.SysGroup.Alcaldia.Servicios.Interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.SysGroup.Alcaldia.Modelos.Empleado;

public interface IEmpleadoService {
    
    Page<Empleado> buscarTodos(Pageable pageable);

    List<Empleado> obtenerTodos();

    Optional<Empleado> buscarPorId(Integer id);

    Empleado crearOeditar(Empleado empleado);

    void eliminarPorId(Integer id);


}
