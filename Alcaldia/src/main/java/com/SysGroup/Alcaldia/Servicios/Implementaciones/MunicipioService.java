package com.SysGroup.Alcaldia.Servicios.Implementaciones;



import java.util.*;

import com.SysGroup.Alcaldia.Servicios.Interfaces.IMunicipioService;
import com.SysGroup.Alcaldia.Modelos.Municipio;
import com.SysGroup.Alcaldia.Repositorios.MunicipioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.data.domain.*;


@Service
public class MunicipioService implements IMunicipioService {
    @Autowired
    private MunicipioRepository municipioRepository;

    @Override
    public Page<Municipio> buscarTodos(Pageable pageable) {
        return municipioRepository.findAll(pageable);
    }

    
    @Override
    public List<Municipio> obtenerTodosLosMunicipios() {
        return municipioRepository.findAll();
    }

    @Override
    public Optional<Municipio> buscaPorId(Integer id) {
        return municipioRepository.findById(id);
    }

    @Override
    public Municipio crearOEditar (Municipio municipio) {
        return municipioRepository.save(municipio);
    }

    @Override
    public void eliminarMunicipio(Integer id) {
        municipioRepository.deleteById(id);
    }

     @Override
public Municipio buscarMunicipioPorId(Integer id) {
    return municipioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Municipio no encontrado con ID: " + id));
}

    
    
}


   

    

    



