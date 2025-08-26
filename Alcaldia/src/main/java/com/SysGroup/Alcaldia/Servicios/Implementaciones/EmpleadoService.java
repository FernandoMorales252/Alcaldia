package com.SysGroup.Alcaldia.Servicios.Implementaciones;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.SysGroup.Alcaldia.Modelos.Empleado;
import com.SysGroup.Alcaldia.Repositorios.IEmpleadoRepository;
import com.SysGroup.Alcaldia.Servicios.Interfaces.IEmpleadoService;
import jakarta.persistence.criteria.Predicate;

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


     // Metodo para PDF
    @Override
    public List<Empleado> buscarEmpleadosFiltrados(LocalDate fechaInicio, LocalDate fechaFin, Integer id_Cargo, Integer id_Municipio, Integer estado) {
        return empleadoRepository.findAll((Specification<Empleado>) (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (fechaInicio != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("fecha_contratacion"), fechaInicio));
            }
            if (fechaFin != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("fecha_contratacion"), fechaFin));
            }
            if (id_Cargo != null) {
                predicates.add(cb.equal(root.get("cargo").get("id_cargo"), id_Cargo));
            }
            if (id_Municipio != null) {
                predicates.add(cb.equal(root.get("municipio").get("id_municipio"), id_Municipio));
            }
            if (estado != null) {
                predicates.add(cb.equal(root.get("estado"), estado));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }
}
