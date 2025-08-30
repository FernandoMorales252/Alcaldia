package com.SysGroup.Alcaldia.Repositorios;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.SysGroup.Alcaldia.Modelos.DocumentoArchivo;

public interface IDocumentoArchivoRepository extends JpaRepository<DocumentoArchivo, Integer>{
    boolean existsByNumeroDocumento(String numeroDocumento);
    Optional<DocumentoArchivo> findByNumeroDocumento(String numeroDocumento);
}
