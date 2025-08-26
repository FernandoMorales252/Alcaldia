package com.SysGroup.Alcaldia.Servicios.Implementaciones;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.SysGroup.Alcaldia.Modelos.Empleado;
import com.SysGroup.Alcaldia.Repositorios.IEmpleadoRepository;
import com.SysGroup.Alcaldia.Servicios.Interfaces.IEmpleadoService;

@Service
public class EmpleadoService implements IEmpleadoService {
    @Autowired
    private IEmpleadoRepository empleadoRepository;
    
    @Override
    public Page<Empleado> buscarTodos(Pageable pageable){
        return empleadoRepository.findAll(pageable);
    }

    @Override
    public   List<Empleado> obtenerTodos(){
       return  empleadoRepository.findAll();
    }

    @Override
    public Empleado buscarPorId(Integer id_empleado){
        return empleadoRepository.findById(id_empleado).get();
    }

    @Override
    public  Empleado crearOeditar(Empleado empleado){
        return empleadoRepository.save(empleado);
    }
    
     @Override
    public  void eliminarPorId(Integer id_empleado){
        empleadoRepository.deleteById(id_empleado);
    }

}
