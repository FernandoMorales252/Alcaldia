package com.SysGroup.Alcaldia.Servicios.Interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.SysGroup.Alcaldia.Modelos.Cargo;

public interface ICargoService {


    Page<Cargo> buscarTodos(Pageable pageable);

    List<Cargo> obtenerTodos();

    Optional<Cargo>  buscarPorId(Integer id_cargo);

    Cargo crearOeditar(Cargo cargo);

    void eliminarPorId(Integer id_cargo);

}
