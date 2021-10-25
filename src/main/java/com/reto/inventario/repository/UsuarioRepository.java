package com.reto.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.reto.inventario.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	public Usuario findByUserName(String username);
	
	@Modifying
	@Query(value = "delete from usuario_roles where id_usuario=?1", nativeQuery = true)
	void deleteUsuarioRoles(Integer idUsuario);
	
}
