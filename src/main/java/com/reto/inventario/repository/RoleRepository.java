package com.reto.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.reto.inventario.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	@Query("select r from Role r where r.nombre=?1")
	public Role findByNombre(String nombre);
}
