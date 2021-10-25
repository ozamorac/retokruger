package com.reto.inventario.service;

import java.util.Date;
import java.util.List;

import com.reto.inventario.dto.EmpleadoFilterDto;
import com.reto.inventario.entities.Empleado;
import com.reto.inventario.request.EmpleadoRequest;
import com.reto.inventario.response.EmpleadoResponse;

public interface EmpleadoService {

	List<EmpleadoFilterDto> findAll();
	
	List<EmpleadoFilterDto> findAllFechas(Date fechaInicio, Date fechaFinal);
	
	List<EmpleadoFilterDto> findAllFechasAndEstVacunacion(Date fechaInicio, 
			Date fechaFinal, String estadoVacunacion);
	
	List<EmpleadoFilterDto> findAllFechasAndTipoVacunacion(Date fechaInicio, 
			Date fechaFinal, String tipoVacunacion);
	
	List<EmpleadoFilterDto> findAllFechasAndTipoAndEstado(Date fechaInicio, 
			Date fechaFinal, String tipoVacunacion, String estadoVacunacion);
	
	List<EmpleadoFilterDto> findAllEstVacunacion(String estadoVacunacion);
	
	List<EmpleadoFilterDto> findAllTipoVacunacion(String tipoVacunacion);
	
	List<EmpleadoFilterDto> findAllTipoAndEstado(String tipoVacunacion, String estadoVacunacion);
	
	Empleado getById(Integer idEmpleado) throws Exception;
	
	EmpleadoResponse saveEmpleado(EmpleadoRequest empleado) throws Exception;
	
	EmpleadoResponse edit(Empleado empleado) throws Exception;
	
	void remove(Integer empleadoCodigo) throws Exception;
	
}
