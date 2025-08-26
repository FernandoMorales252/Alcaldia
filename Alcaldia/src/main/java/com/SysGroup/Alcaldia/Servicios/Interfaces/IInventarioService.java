package com.SysGroup.Alcaldia.Servicios.Interfaces;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.SysGroup.Alcaldia.Modelos.Inventario;

public interface IInventarioService {

     Page<Inventario> buscarTodos(Pageable pageable);

    List<Inventario> obtenerTodos();

    Optional<Inventario>  buscarPorId(Integer id_inventario);

    Inventario crearOeditar(Inventario inventario);

    void eliminarPorId(Integer id_inventario);

}