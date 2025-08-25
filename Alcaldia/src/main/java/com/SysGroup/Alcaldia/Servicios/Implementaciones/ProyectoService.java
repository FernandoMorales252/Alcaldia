package com.SysGroup.Alcaldia.Servicios.Implementaciones;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.SysGroup.Alcaldia.Modelos.Proyecto;

import com.SysGroup.Alcaldia.Repositorios.IProyectoRepository;
import com.SysGroup.Alcaldia.Servicios.Interfaces.IProyectoService;


@Service
public class ProyectoService implements IProyectoService {
   @Autowired
    private IProyectoRepository proyectoRepository;

    @Override
    public List<Proyecto> obtenerTodos() {
        return proyectoRepository.findAll();
    }

    @Override
    public Page<Proyecto> buscarTodosPaginados(Pageable pageable) {
        return proyectoRepository.findAll(pageable);
    }

    @Override
    public Proyecto buscarPorId(Integer id) {
        return proyectoRepository.findById(id).get();
    }

    @Override
    public Proyecto crearOEditar(Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    @Override
    public void eliminarPorId(Integer id) {
        proyectoRepository.deleteById(id);
    }

   

}