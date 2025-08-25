package com.SysGroup.Alcaldia.Servicios.Interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.SysGroup.Alcaldia.Modelos.Municipio;


public interface IMunicipioService {
    Page<Municipio> buscarTodos(Pageable pageable);
    List<Municipio> obtenerTodosLosMunicipios();
    void eliminarMunicipio(Integer id);
    Municipio crearOEditar(Municipio municipio);
    Optional<Municipio> buscaPorId(Integer id);
}
