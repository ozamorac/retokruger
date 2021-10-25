package com.reto.inventario.dto;

import java.util.Date;

public interface EmpleadoFilterDto {

	long getIdEmpleado();
	
	String getCedula();
	
	String getNombres();
	
	String getApellidos();
	
	Date getFechaVacunacion();
	
	String getTipoVacuna();
	
	Integer getDosis();
	
}
