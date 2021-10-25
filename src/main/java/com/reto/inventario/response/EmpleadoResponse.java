package com.reto.inventario.response;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoResponse {

	private Integer idEmpleado;
	
	@Size(min=10, max=10, message="debe ser de 10 dígitos")
	@Pattern(regexp = ".*[0-9]", message="debe ser tener al meno un número")
	private String cedula;
	
	@NotEmpty(message = "Email no puede ser un campo vacío")
	@Pattern(regexp = "[a-zA-Z\\t\\h]+|(^$)", message="solo debe ser letras sin caracteres especiales")
	private String nombres;
	
	@NotEmpty(message = "Email no puede ser un campo vacío")
	@Pattern(regexp = "[a-zA-Z\\t\\h]+|(^$)", message="solo debe ser letras sin caracteres especiales")
	private String apellidos;

	private String correoElectronico;

	private String direccion;

	//@DateTimeFormat(iso = ISO.DATE)
    //@JsonFormat(timezone = "America/Lima")
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;
	
	private String telefonoMovil;
	
	private String estadoVacunacion;
	
}
