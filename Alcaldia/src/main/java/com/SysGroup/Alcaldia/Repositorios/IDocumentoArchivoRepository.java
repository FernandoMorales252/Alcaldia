package com.SysGroup.Alcaldia.Repositorios;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import com.SysGroup.Alcaldia.Modelos.DocumentoArchivo;

public interface IDocumentoArchivoRepository extends JpaRepository<DocumentoArchivo, Integer>{
Page<DocumentoArchivo> findByDocumentoDesc(Pageable pageable);
}
