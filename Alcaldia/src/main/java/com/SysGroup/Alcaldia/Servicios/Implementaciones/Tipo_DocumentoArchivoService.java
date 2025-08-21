package com.SysGroup.Alcaldia.Servicios.Implementaciones;
import java.util.*;

import com.SysGroup.Alcaldia.Servicios.Interfaces.ITipo_DocumentoArchivoService;
import com.SysGroup.Alcaldia.Modelos.Tipo_DocumentoArchivo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.data.domain.*;

@Service
public class Tipo_DocumentoArchivoService implements ITipo_DocumentoArchivoService {
    @Autowired
    private com.SysGroup.Alcaldia.Repositorios.Tipo_DocumentoArchivoRepository tipoDocumentoArchivoRepository;

    @Override
    public Page<Tipo_DocumentoArchivo> buscarTodos(Pageable pageable) {
        return tipoDocumentoArchivoRepository.findAll(pageable);
    }

    @Override
    public List<Tipo_DocumentoArchivo> obtenerTodos() {
        return tipoDocumentoArchivoRepository.findAll();
    }

    @Override
    public Optional<Tipo_DocumentoArchivo> buscarPorId(Integer id) {
        return tipoDocumentoArchivoRepository.findById(id);
    }

    @Override
    public Tipo_DocumentoArchivo crearOEditar(Tipo_DocumentoArchivo tipoDocumentoArchivo) {
        return tipoDocumentoArchivoRepository.save(tipoDocumentoArchivo);
    }

    @Override
    public void eliminarPorId(Integer id) {
        tipoDocumentoArchivoRepository.deleteById(id);
    }

}
