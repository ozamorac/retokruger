package com.reto.inventario.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.reto.inventario.dto.EmpleadoFilterDto;
import com.reto.inventario.entities.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

	@Query(value = "select e.id_empleado as idEmpleado, e.cedula as cedula, e.nombres as nombres, e.apellidos as apellidos, e.estado_vacunacion as estadoVacunacion, v.fecha_vacunacion as fechaVacunacion, v.tipo_vacuna as tipoVacuna, v.dosis as dosis "
			+ "from empleado e left join vacuna v on e.id_empleado = v.id_empleado "
			+ "group by e.id_empleado, v.fecha_vacunacion, v.tipo_vacuna, v.dosis order by e.apellidos, e.nombres", nativeQuery = true)
	public List<EmpleadoFilterDto> getAll();
	
	@Query(value = "select e.id_empleado as idEmpleado, e.cedula as cedula, e.nombres as nombres, e.apellidos as apellidos, e.estado_vacunacion as estadoVacunacion, v.fecha_vacunacion as fechaVacunacion, v.tipo_vacuna as tipoVacuna, v.dosis as dosis "
			+ "from empleado e left join vacuna v on e.id_empleado = v.id_empleado where v.fecha_vacunacion >= ?1 and v.fecha_vacunacion <= ?2 "
			+ "group by e.id_empleado, v.fecha_vacunacion, v.tipo_vacuna, v.dosis order by e.apellidos, e.nombres", nativeQuery = true)
	public List<EmpleadoFilterDto> getAllFechas(Date fechaInicio, Date fechaFinal);
	
	@Query(value = "select e.id_empleado as idEmpleado, e.cedula as cedula, e.nombres as nombres, e.apellidos as apellidos, e.estado_vacunacion as estadoVacunacion, v.fecha_vacunacion as fechaVacunacion, v.tipo_vacuna as tipoVacuna, v.dosis as dosis "
			+ "from empleado e left join vacuna v on e.id_empleado = v.id_empleado where v.fecha_vacunacion >= ?1 and v.fecha_vacunacion <= ?2 "
			+ " and e.estado_vacunacion = ?3 "
			+ "group by e.id_empleado, v.fecha_vacunacion, v.tipo_vacuna, v.dosis order by e.apellidos, e.nombres", nativeQuery = true)
	public List<EmpleadoFilterDto> getAllFechasAndEstado(Date fechaInicio, Date fechaFinal, String estado);
	
	@Query(value = "select e.id_empleado as idEmpleado, e.cedula as cedula, e.nombres as nombres, e.apellidos as apellidos, e.estado_vacunacion as estadoVacunacion, v.fecha_vacunacion as fechaVacunacion, v.tipo_vacuna as tipoVacuna, v.dosis as dosis "
			+ "from empleado e left join vacuna v on e.id_empleado = v.id_empleado where v.fecha_vacunacion >= ?1 and v.fecha_vacunacion <= ?2 "
			+ " and v.tipo_vacuna = ?3 "
			+ "group by e.id_empleado, v.fecha_vacunacion, v.tipo_vacuna, v.dosis order by e.apellidos, e.nombres", nativeQuery = true)
	public List<EmpleadoFilterDto> getAllFechasAndTipo(Date fechaInicio, Date fechaFinal, String tipo);
	
	
	@Query(value = "select e.id_empleado as idEmpleado, e.cedula as cedula, e.nombres as nombres, e.apellidos as apellidos, e.estado_vacunacion as estadoVacunacion, v.fecha_vacunacion as fechaVacunacion, v.tipo_vacuna as tipoVacuna, v.dosis as dosis "
			+ "from empleado e left join vacuna v on e.id_empleado = v.id_empleado where v.fecha_vacunacion >= ?1 and v.fecha_vacunacion <= ?2 "
			+ " and v.tipo_vacuna = ?3 and e.estado_vacunacion = ?4 "
			+ "group by e.id_empleado, v.fecha_vacunacion, v.tipo_vacuna, v.dosis order by e.apellidos, e.nombres", nativeQuery = true)
	public List<EmpleadoFilterDto> findAllFechasAndTipoAndEstado(Date fechaInicio, Date fechaFinal, String tipo, String estado);
	
	@Query(value = "select e.id_empleado as idEmpleado, e.cedula as cedula, e.nombres as nombres, e.apellidos as apellidos, e.estado_vacunacion as estadoVacunacion, v.fecha_vacunacion as fechaVacunacion, v.tipo_vacuna as tipoVacuna, v.dosis as dosis "
			+ "from empleado e left join vacuna v on e.id_empleado = v.id_empleado where  e.estado_vacunacion = ?1 "
			+ "group by e.id_empleado, v.fecha_vacunacion, v.tipo_vacuna, v.dosis order by e.apellidos, e.nombres", nativeQuery = true)
	public List<EmpleadoFilterDto> getAllEstado(String estado);
	
	@Query(value = "select e.id_empleado as idEmpleado, e.cedula as cedula, e.nombres as nombres, e.apellidos as apellidos, e.estado_vacunacion as estadoVacunacion, v.fecha_vacunacion as fechaVacunacion, v.tipo_vacuna as tipoVacuna, v.dosis as dosis "
			+ "from empleado e left join vacuna v on e.id_empleado = v.id_empleado where v.tipo_vacuna = ?1 "
			+ "group by e.id_empleado, v.fecha_vacunacion, v.tipo_vacuna, v.dosis order by e.apellidos, e.nombres", nativeQuery = true)
	public List<EmpleadoFilterDto> getAllTipo(String tipo);
	
	
	@Query(value = "select e.id_empleado as idEmpleado, e.cedula as cedula, e.nombres as nombres, e.apellidos as apellidos, e.estado_vacunacion as estadoVacunacion, v.fecha_vacunacion as fechaVacunacion, v.tipo_vacuna as tipoVacuna, v.dosis as dosis "
			+ "from empleado e left join vacuna v on e.id_empleado = v.id_empleado where v.tipo_vacuna = ?1 and e.estado_vacunacion = ?2 "
			+ "group by e.id_empleado, v.fecha_vacunacion, v.tipo_vacuna, v.dosis order by e.apellidos, e.nombres", nativeQuery = true)
	public List<EmpleadoFilterDto> findAllTipoAndEstado(String tipo, String estado);
	
}
