package com.SysGroup.Alcaldia.Servicios.Implementaciones;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.SysGroup.Alcaldia.Modelos.DocumentoArchivo;
import com.SysGroup.Alcaldia.Repositorios.IDocumentoArchivoRepository;
import com.SysGroup.Alcaldia.Servicios.Interfaces.IDocumentoArchivoService;

@Service
public class DocumentoArchivoService implements IDocumentoArchivoService  {
    @Autowired
    private IDocumentoArchivoRepository documentoArchivoRepository;

    @Override
    public List<DocumentoArchivo> obtenerTodos() {
        return documentoArchivoRepository.findAll();
    }

    @Override
    public Page<DocumentoArchivo> buscarTodosPaginados(Pageable pageable) {
        return documentoArchivoRepository.findAll(pageable);
    }

    @Override
    public DocumentoArchivo buscarPorId(Integer id) {
        return documentoArchivoRepository.findById(id).orElse(null);
    }

    @Override
    public DocumentoArchivo crearOEditar(DocumentoArchivo documentoArchivo) {
        return documentoArchivoRepository.save(documentoArchivo);
    }

    @Override
    public void eliminarPorId(Integer id) {
        documentoArchivoRepository.deleteById(id);
    }

}
