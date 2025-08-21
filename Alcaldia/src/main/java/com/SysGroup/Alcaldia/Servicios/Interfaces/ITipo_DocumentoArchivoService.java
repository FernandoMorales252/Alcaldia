package com.SysGroup.Alcaldia.Servicios.Interfaces;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.SysGroup.Alcaldia.Modelos.Tipo_DocumentoArchivo;

public interface ITipo_DocumentoArchivoService {
Page<Tipo_DocumentoArchivo> buscarTodos(Pageable pageable);
List<Tipo_DocumentoArchivo> obtenerTodos();
Optional<Tipo_DocumentoArchivo> buscarPorId(Integer id);
Tipo_DocumentoArchivo crearOEditar(Tipo_DocumentoArchivo tipoDocumentoArchivo);
void eliminarPorId(Integer id);

}
