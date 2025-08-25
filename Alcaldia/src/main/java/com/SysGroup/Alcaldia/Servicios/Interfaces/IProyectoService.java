package com.SysGroup.Alcaldia.Servicios.Interfaces;
import java.util.List;

import com.SysGroup.Alcaldia.Modelos.Proyecto;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface IProyectoService {
    // Obtener todos los proyectos
    List<Proyecto> obtenerTodos();

    // Buscar proyectos con paginación
    Page<Proyecto> buscarTodosPaginados(Pageable pageable);

    // Buscar un proyecto por ID
    Proyecto buscarPorId(Integer id);

    // Crear o editar un proyecto
    Proyecto crearOEditar(Proyecto proyecto);

    // Eliminar un proyecto por ID
    void eliminarPorId(Integer id);

   
  


}
