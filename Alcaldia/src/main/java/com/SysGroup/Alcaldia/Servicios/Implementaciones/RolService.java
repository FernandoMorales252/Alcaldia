package com.SysGroup.Alcaldia.Servicios.Implementaciones;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SysGroup.Alcaldia.Repositorios.IRolRepository;
import com.SysGroup.Alcaldia.Servicios.Interfaces.IRolService;
import com.SysGroup.Alcaldia.Modelos.Rol;

@Service
public class RolService implements IRolService {

    @Autowired
    private IRolRepository rolRepository;

    @Override
    public List<Rol> obtenerTodos() {
        return rolRepository.findAll();
    }

}