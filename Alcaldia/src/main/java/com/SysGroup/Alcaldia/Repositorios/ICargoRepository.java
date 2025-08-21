package com.SysGroup.Alcaldia.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SysGroup.Alcaldia.Modelos.Cargo;

public interface ICargoRepository extends JpaRepository<Cargo, Integer> {

}
