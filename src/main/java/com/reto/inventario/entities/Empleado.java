package com.reto.inventario.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="empleado")
public class Empleado implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_empleado")
	private Integer idEmpleado;
	
	@Size(min=10, max=10, message="debe ser de 10 dígitos")
	@Pattern(regexp = ".*[0-9]", message="debe tener al menos un número")
	private String cedula;
	
	@NotEmpty(message = "no puede ser un campo vacío")
	@Pattern(regexp = "[a-zA-Z\\t\\h]+|(^$)", message="solo debe ser letras sin caracteres especiales")
	private String nombres;
	
	@NotEmpty(message = "no puede ser un campo vacío")
	@Pattern(regexp = "[a-zA-Z\\t\\h]+|(^$)", message="solo debe ser letras sin caracteres especiales")
	private String apellidos;

	@Column(name="correo_electronico")
	@Email(message = "debe ser un email válido", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
	@NotEmpty(message = "no puede ser un campo vacío")
	private String correoElectronico;

	private String direccion;
	
	@Column(name="fecha_nacimiento")
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;
	
	@Column(name="telefono_movil")
	private String telefonoMovil;
	
	@Column(name="estado_vacunacion")
	private String estadoVacunacion;
	
	@OneToMany(mappedBy="empleado", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Usuario> usuarios;
	
	@OneToMany(mappedBy="empleado", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Vacuna> vacunas;
	
	public Empleado() {
	}

	public Integer getIdEmpleado() {
		return this.idEmpleado;
	}

	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getCorreoElectronico() {
		return this.correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEstadoVacunacion() {
		return this.estadoVacunacion;
	}

	public void setEstadoVacunacion(String estadoVacunacion) {
		this.estadoVacunacion = estadoVacunacion;
	}

	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getTelefonoMovil() {
		return this.telefonoMovil;
	}

	public void setTelefonoMovil(String telefonoMovil) {
		this.telefonoMovil = telefonoMovil;
	}
	
	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Vacuna> getVacunas() {
		return vacunas;
	}

	public void setVacunas(List<Vacuna> vacunas) {
		this.vacunas = vacunas;
	}
	
}