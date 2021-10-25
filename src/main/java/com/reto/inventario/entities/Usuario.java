package com.reto.inventario.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="usuario")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_usuario")
	private Integer idUsuario;
	
	@Column(name="username", unique=true, length=15)
	private String userName;
	
	private String password;
	
	private String estado;
	
	@Column(name="password_nencrypt")
	private String passwordNencrypt;

	@ManyToOne
	@JoinColumn(name="id_empleado")
	private Empleado empleado;

	@ManyToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(
		name="usuario_roles"
		, joinColumns={
			@JoinColumn(name="id_usuario")
			}
		, inverseJoinColumns=
			@JoinColumn(name="id_rol"),
			uniqueConstraints= {@UniqueConstraint(columnNames= {"id_usuario", "id_rol"})
			}
		)
	
	private List<Role> roles;

	public Usuario() {
	}

	public Integer getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String username) {
		this.userName = username;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getPasswordNencrypt() {
		return passwordNencrypt;
	}

	public void setPasswordNencrypt(String passwordNencrypt) {
		this.passwordNencrypt = passwordNencrypt;
	}

	public Empleado getEmpleado() {
		return this.empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	
}