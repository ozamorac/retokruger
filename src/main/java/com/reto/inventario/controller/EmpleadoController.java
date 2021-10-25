package com.reto.inventario.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reto.inventario.dto.EmpleadoFilterDto;
import com.reto.inventario.entities.Empleado;
import com.reto.inventario.request.EmpleadoRequest;
import com.reto.inventario.request.EmpleadoRequestFilter;
import com.reto.inventario.response.EmpleadoResponse;
import com.reto.inventario.service.EmpleadoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/empleados")
@Api(value = "Servicio de Empleados")
public class EmpleadoController {

	@Autowired
	private EmpleadoService empleadoService;

	@GetMapping
	@ApiOperation(value = "Obtener Lista de Empleados, segun filtro de Fechas de Vacunación, estado de Vacunación y Tipo de Vacuna", notes = "Servicio que permite devolver la lista de empleados")
	public ResponseEntity<?> getAll(@RequestBody EmpleadoRequestFilter request) {

		try {
			List<EmpleadoFilterDto> clienteList = null;
			
			if (request.getFechaVacunacionInicio() != null && request.getFechaVacunacionHasta() != null) {
				if (request.getFechaVacunacionHasta().before(request.getFechaVacunacionInicio())) {
					return buildResponseError("La fecha de consulta de Inicio de Vacunación debe ser menor que la fecha de consulta final", null);
				} else {
					if (request.getEstadoVacunacion() != "" && request.getTipoVacuna() == "") {
						clienteList = empleadoService.findAllFechasAndEstVacunacion(request.getFechaVacunacionInicio(), request.getFechaVacunacionHasta(), 
								request.getEstadoVacunacion());
					} else if(request.getEstadoVacunacion() == "" && request.getTipoVacuna() != "") {
						clienteList = empleadoService.findAllFechasAndTipoVacunacion(request.getFechaVacunacionInicio(), request.getFechaVacunacionHasta(), 
								request.getTipoVacuna());
					} else if (request.getEstadoVacunacion() != "" && request.getTipoVacuna() != "") {
						clienteList = empleadoService.findAllFechasAndTipoAndEstado(request.getFechaVacunacionInicio(), request.getFechaVacunacionHasta(), 
								request.getTipoVacuna(), request.getEstadoVacunacion());
					} else {
						clienteList = empleadoService.findAllFechas(request.getFechaVacunacionInicio(), request.getFechaVacunacionHasta());
					}
				}
				
			} else {
				if (request.getFechaVacunacionInicio() == null && request.getFechaVacunacionHasta() != null) {
					return buildResponseError("Error en fechas, debe ingresar los datos de fechas", null);
				}
				if (request.getFechaVacunacionInicio() != null && request.getFechaVacunacionHasta() == null) {
					return buildResponseError("Error en fechas, debe ingresar los datos de fechas", null);
				}
				
				if (request.getEstadoVacunacion() != "" && request.getTipoVacuna() == "") {
					clienteList = empleadoService.findAllEstVacunacion(request.getEstadoVacunacion());
				} else if(request.getEstadoVacunacion() == "" && request.getTipoVacuna() != "") {
					clienteList = empleadoService.findAllTipoVacunacion(request.getTipoVacuna());
				} else if (request.getEstadoVacunacion() != "" && request.getTipoVacuna() != "") {
					clienteList = empleadoService.findAllTipoAndEstado(request.getTipoVacuna(), request.getEstadoVacunacion());
				} else {
					clienteList = empleadoService.findAll();
				}
					
			}

			if (clienteList == null || clienteList.size()==0) {
				return buildResponseError("No se encontraron datos en la base de datos", null);
			}
			
			return new ResponseEntity<List<EmpleadoFilterDto>>(clienteList, HttpStatus.OK);
		} catch (Exception e) {
			return buildResponseError("Error al obtener lista de Empleados", e);
		}
	}

	@PostMapping("/crear")
	@ApiOperation(value = "Crear un Empleado", notes = "Servicio que permite crear un empleado")
	public ResponseEntity<?> create(@Valid @RequestBody EmpleadoRequest empleado, BindingResult result) {

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			return buildResponseError(errors);
		}
		
		EmpleadoResponse newEmpleado = null;
		
		try {
			
			newEmpleado = empleadoService.saveEmpleado(empleado);

		} catch (Exception e) {
			return buildResponseError("Error al registrar Empleado", e);
		}

		return new ResponseEntity<EmpleadoResponse>(newEmpleado, HttpStatus.CREATED);

	}

	@PutMapping("/{empleadoCodigo}")
	@ApiOperation(value = "Editar un Empleado", notes = "Servicio que permite editar un empleado")
	public ResponseEntity<?> update(@RequestBody EmpleadoRequest empleado, @PathVariable Integer empleadoCodigo) {

		EmpleadoResponse updateEmpleado = null;
		empleado.setIdEmpleado(empleadoCodigo);
		
		try {
			
			updateEmpleado = empleadoService.saveEmpleado(empleado);

		} catch (Exception e) {
			return buildResponseError("Error al editar Empleado", e);
		}

		return new ResponseEntity<EmpleadoResponse>(updateEmpleado, HttpStatus.OK);
	}

	@DeleteMapping("/{empleadoCodigo}")
	@ApiOperation(value = "Eliminar un Empleado", notes = "Servicio que permite eliminar un empleado")
	public ResponseEntity<?> remove(@PathVariable Integer empleadoCodigo) {

		try {
			empleadoService.remove(empleadoCodigo);
		} catch (Exception e) {
			return buildResponseError("Error al eliminar Empleado", e);
		}

		Map<String, Object> response = new HashMap<>();
		response.put("mensaje", "El Empleado ha sido eliminado con éxito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	private ResponseEntity<Map<String, Object>> buildResponseError(String error, Exception exception) {
		Map<String, Object> response = new HashMap<>();

		HttpStatus httpStatus = HttpStatus.NOT_FOUND;
		if (exception != null) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			if (exception.getMessage() != null) {
				error = exception.getMessage();
			}
		}
		response.put("Mensaje", error);
		return new ResponseEntity<Map<String, Object>>(response, httpStatus);
	}
	
	private ResponseEntity<Map<String, Object>> buildResponseError(List<String> errors) {
		Map<String, Object> response = new HashMap<>();
		response.put("Mensaje", errors);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
	}
}
