package com.SysGroup.Alcaldia.Servicios.Interfaces;

import java.time.LocalDate;
import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.SysGroup.Alcaldia.Modelos.Empleado;

public interface IEmpleadoService {
    
    Page<Empleado> buscarTodos(Pageable pageable);

    List<Empleado> obtenerTodos();

    Empleado buscarPorId(Integer id_empleado);

    Empleado crearOeditar(Empleado empleado);

    void eliminarPorId(Integer id_empleado);

      //Metodo para PDF
    List<Empleado> buscarEmpleadosFiltrados(LocalDate fechaInicio, LocalDate fechaFin, Integer id_Cargo, Integer id_Municipio, Integer estado);


}
