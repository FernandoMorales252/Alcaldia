package com.SysGroup.Alcaldia.Servicios.Implementaciones;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import com.SysGroup.Alcaldia.Servicios.Interfaces.ICargoService;
import com.SysGroup.Alcaldia.Modelos.Cargo;
import com.SysGroup.Alcaldia.Repositorios.ICargoRepository;

@Service
public class CargoService  implements ICargoService{


     @Autowired
    private ICargoRepository cargoRepository;
    
    @Override
    public Page<Cargo> buscarTodos(Pageable pageable){
        return cargoRepository.findAll(pageable);
    }

    @Override
    public   List<Cargo> obtenerTodos(){
       return  cargoRepository.findAll();
    }

    @Override
    public Optional<Cargo>  buscarPorId(Integer id){
        return cargoRepository.findById(id);
    }

    @Override
    public  Cargo crearOeditar(Cargo cargo){
        return cargoRepository.save(cargo);
    }
    
     @Override
    public  void eliminarPorId(Integer id){
        cargoRepository.deleteById(id);
    }

}
