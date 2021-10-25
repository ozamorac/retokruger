package com.reto.inventario.request;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoRequest {

	private Integer idEmpleado;
	
	@Size(min=10, max=10, message="debe ser de 10 dígitos")
	@Pattern(regexp = ".*[0-9]", message="debe tener al menos un número")
	private String cedula;
	
	@NotEmpty(message = "Email no puede ser un campo vacío")
	@Pattern(regexp = "[a-zA-Z\\t\\h]+|(^$)", message="solo debe ser letras sin caracteres especiales")
	private String nombres;
	
	@NotEmpty(message = "Email no puede ser un campo vacío")
	@Pattern(regexp = "[a-zA-Z\\t\\h]+|(^$)", message="solo debe ser letras sin caracteres especiales")
	private String apellidos;

	@Email(message = "debe ser un email válido", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
	@NotEmpty(message = "no puede ser un campo vacío")
	private String correoElectronico;

	private String direccion;

	@DateTimeFormat(iso = ISO.DATE)
    @JsonFormat(timezone = "America/Lima")
	private Date fechaNacimiento;
	
	private String telefonoMovil;
	
	private String estadoVacunacion;
	
	@NotEmpty(message = "no puede ser un campo vacío")
	private String rol;
	
	@Valid
	private List<VacunaRequest> vacunas = new ArrayList<>();
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class VacunaRequest {
		
		private Integer idVacuna;

		private Integer dosis;

		@DateTimeFormat(iso = ISO.DATE)
	    @JsonFormat(timezone = "America/Lima")
		private Date fechaVacunacion;
		
		private String tipoVacuna;
		
	}
	
}
