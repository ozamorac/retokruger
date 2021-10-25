package com.reto.inventario.request;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoRequestFilter {
	
	private String estadoVacunacion;
	
	private String tipoVacuna;
	
	@DateTimeFormat(iso = ISO.DATE)
    @JsonFormat(timezone = "America/Lima")
	private Date fechaVacunacionInicio;
	
	@DateTimeFormat(iso = ISO.DATE)
    @JsonFormat(timezone = "America/Lima")
	private Date fechaVacunacionHasta;

}
