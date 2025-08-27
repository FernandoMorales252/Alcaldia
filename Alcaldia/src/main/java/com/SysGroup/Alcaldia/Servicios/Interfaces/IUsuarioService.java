package com.SysGroup.Alcaldia.Servicios.Interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.SysGroup.Alcaldia.Modelos.Usuario;

public interface IUsuarioService {
    Page<Usuario> obtenerTodosPaginados(Pageable pageable);
    List<Usuario> obtenerTodos();
    Usuario obtenerPorId(Integer id);
    Usuario crearOEditar(Usuario usuario);
    void eliminarPorId(Integer id);

}