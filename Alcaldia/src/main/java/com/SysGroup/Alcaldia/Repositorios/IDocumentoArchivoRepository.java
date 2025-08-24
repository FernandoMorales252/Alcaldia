package com.SysGroup.Alcaldia.Repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.SysGroup.Alcaldia.Modelos.DocumentoArchivo;

public interface IDocumentoArchivoRepository extends JpaRepository<DocumentoArchivo, Integer>{
List<DocumentoArchivo> findByNumerodocumento(String numerodocumento);
}
