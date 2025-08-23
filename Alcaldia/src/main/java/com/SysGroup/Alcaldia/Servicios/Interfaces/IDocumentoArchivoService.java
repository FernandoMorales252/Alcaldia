package com.SysGroup.Alcaldia.Servicios.Interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.SysGroup.Alcaldia.Modelos.DocumentoArchivo;

public interface IDocumentoArchivoService {
  List<DocumentoArchivo> obtenerTodos();
    Page<DocumentoArchivo> buscarTodosPaginados(Pageable pageable);
    DocumentoArchivo buscarPorId(Integer id);
    DocumentoArchivo crearOEditar(DocumentoArchivo documentoArchivo);
    void eliminarPorId(Integer id);
}
