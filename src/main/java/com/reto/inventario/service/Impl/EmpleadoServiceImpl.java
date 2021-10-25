package com.reto.inventario.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reto.inventario.dto.EmpleadoFilterDto;
import com.reto.inventario.entities.Empleado;
import com.reto.inventario.entities.Role;
import com.reto.inventario.entities.Usuario;
import com.reto.inventario.entities.Vacuna;
import com.reto.inventario.repository.EmpleadoRepository;
import com.reto.inventario.repository.RoleRepository;
import com.reto.inventario.repository.UsuarioRepository;
import com.reto.inventario.request.EmpleadoRequest;
import com.reto.inventario.request.EmpleadoRequest.VacunaRequest;
import com.reto.inventario.response.EmpleadoResponse;
import com.reto.inventario.service.EmpleadoService;
import com.reto.inventario.utils.Utils;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	@Transactional(readOnly = true)
	public List<EmpleadoFilterDto> findAll() {
		return empleadoRepository.getAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Empleado getById(Integer idEmpleado) throws Exception {
		return empleadoRepository.findById(idEmpleado).orElse(null);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public EmpleadoResponse saveEmpleado(EmpleadoRequest empleado) throws Exception {

		Empleado newEmpleado = null;
		EmpleadoResponse empleadoResponse = new EmpleadoResponse();
		Empleado empleadoCreate = new Empleado();
		try {
			if (empleado.getIdEmpleado() == null) {
				empleadoCreate.setCedula(empleado.getCedula());
				empleadoCreate.setNombres(empleado.getNombres());
				empleadoCreate.setApellidos(empleado.getApellidos());
				empleadoCreate.setCorreoElectronico(empleado.getCorreoElectronico());
				newEmpleado = empleadoRepository.save(empleadoCreate);
				
				Role role= null;
				role = roleRepository.findByNombre(empleado.getRol());
				
				List<Role> lisRole= new ArrayList<>();
				lisRole.add(role);
				
				String userName = empleado.getNombres().charAt(0) + empleado.getApellidos().substring(0,8);
	
				Usuario usuario = new Usuario();
				
				String password = Utils.generateRandomPassword(8);
				
				String passwordBcrypt = passwordEncoder.encode(password);
				
				usuario.setEmpleado(newEmpleado);
				usuario.setUserName(userName.replaceAll("\\s",""));
				usuario.setPassword(passwordBcrypt);
				usuario.setPasswordNencrypt(password);
				usuario.setRoles(lisRole);
				usuario.setEstado("A");
	
				Usuario usuarioNew = usuarioRepository.save(usuario);
				List<Usuario> listUsuarioNew = new ArrayList<>();
				listUsuarioNew.add(usuarioNew);
				
				empleadoResponse.setIdEmpleado(newEmpleado.getIdEmpleado());
				empleadoResponse.setCedula(newEmpleado.getCedula());
				empleadoResponse.setNombres(newEmpleado.getNombres());
				empleadoResponse.setApellidos(newEmpleado.getApellidos());
				empleadoResponse.setCorreoElectronico(newEmpleado.getCorreoElectronico());
				
			} else {
				
				Empleado currentEmpleado = empleadoRepository.findById(empleado.getIdEmpleado()).orElse(null);

				if (currentEmpleado == null) {
					String valueCodigo = "" + empleado.getIdEmpleado();
					throw new Exception("Error: no se pudo editar, el Empleado: "
							.concat(valueCodigo.concat(" no existe en la base de datos!")));
				} else {
					
					Empleado updatedEmpleado = null;

					currentEmpleado.setFechaNacimiento(empleado.getFechaNacimiento());
					currentEmpleado.setDireccion(empleado.getDireccion());
					currentEmpleado.setTelefonoMovil(empleado.getTelefonoMovil());
					currentEmpleado.setEstadoVacunacion(empleado.getEstadoVacunacion());
					
					if (empleado.getEstadoVacunacion().equals("S")) {
						List<Vacuna> listVacunas = new ArrayList<>();
						
						for (VacunaRequest vacunaRequest : empleado.getVacunas()) {
							Vacuna vacuna = new Vacuna();
							
							vacuna.setEmpleado(currentEmpleado);
							vacuna.setFechaVacunacion(vacunaRequest.getFechaVacunacion());
							vacuna.setTipoVacuna(vacunaRequest.getTipoVacuna());
							vacuna.setDosis(vacunaRequest.getDosis());
							
							listVacunas.add(vacuna);
						}
						currentEmpleado.setVacunas(listVacunas);
					}
					
					updatedEmpleado = empleadoRepository.save(currentEmpleado);
					
					empleadoResponse.setIdEmpleado(updatedEmpleado.getIdEmpleado());
					empleadoResponse.setCedula(updatedEmpleado.getCedula());
					empleadoResponse.setNombres(updatedEmpleado.getNombres());
					empleadoResponse.setApellidos(updatedEmpleado.getApellidos());
					empleadoResponse.setCorreoElectronico(updatedEmpleado.getCorreoElectronico());
					empleadoResponse.setFechaNacimiento(updatedEmpleado.getFechaNacimiento());
					empleadoResponse.setDireccion(updatedEmpleado.getDireccion());
					empleadoResponse.setTelefonoMovil(updatedEmpleado.getTelefonoMovil());
					empleadoResponse.setEstadoVacunacion(updatedEmpleado.getEstadoVacunacion());
					
				}

			}

		} catch (DataAccessException e) {
			throwDataAccessException(e);
		}

		return empleadoResponse;
	}

	@Override
	@Transactional
	public EmpleadoResponse edit(Empleado empleado) throws Exception {
		
		EmpleadoResponse empleadoResponse = new EmpleadoResponse();
		Empleado updatedEmpleado = null;

		try {
			
			updatedEmpleado = empleadoRepository.save(empleado);
			
			empleadoResponse.setIdEmpleado(updatedEmpleado.getIdEmpleado());
			empleadoResponse.setCedula(updatedEmpleado.getCedula());
			empleadoResponse.setNombres(updatedEmpleado.getNombres());
			empleadoResponse.setApellidos(updatedEmpleado.getApellidos());
			empleadoResponse.setCorreoElectronico(updatedEmpleado.getCorreoElectronico());
			empleadoResponse.setFechaNacimiento(updatedEmpleado.getFechaNacimiento());
			empleadoResponse.setDireccion(updatedEmpleado.getDireccion());
			empleadoResponse.setTelefonoMovil(updatedEmpleado.getTelefonoMovil());
			empleadoResponse.setEstadoVacunacion(updatedEmpleado.getEstadoVacunacion());
			
		} catch (DataAccessException e) {
			throwDataAccessException(e);
		}

		return empleadoResponse;
		
	}

	@Override
	@Transactional
	public void remove(Integer empleadoCodigo) throws Exception {
		try {
			
			Empleado empleado = empleadoRepository.findById(empleadoCodigo).orElse(null);
			
			if (empleado == null) {
				throw new Exception("Error: No se puede eliminar, violación de retricción");
			}
			
			for (Usuario usuario : empleado.getUsuarios()) {
				
				usuarioRepository.deleteUsuarioRoles(usuario.getIdUsuario());
				usuarioRepository.deleteById(usuario.getIdUsuario());

			}
				
			empleadoRepository.deleteById(empleadoCodigo);
			
		} catch (DataAccessException e) {
			throwDataAccessException(e);
		}
	}
	
	private void throwDataAccessException(DataAccessException exception) throws Exception {
		throw new Exception(exception.getMessage().concat(": ").concat(exception.getMostSpecificCause().getMessage()));
	}

	@Override
	@Transactional(readOnly = true)
	public List<EmpleadoFilterDto> findAllFechas(Date fechaInicio, Date fechaFinal) {
		return empleadoRepository.getAllFechas(fechaInicio, fechaFinal);
	}

	@Override
	@Transactional(readOnly = true)
	public List<EmpleadoFilterDto> findAllFechasAndEstVacunacion(Date fechaInicio, Date fechaFinal,
			String estadoVacunacion) {
		return empleadoRepository.getAllFechasAndEstado(fechaInicio, fechaFinal, estadoVacunacion);
	}

	@Override
	@Transactional(readOnly = true)
	public List<EmpleadoFilterDto> findAllFechasAndTipoVacunacion(Date fechaInicio, Date fechaFinal,
			String tipoVacunacion) {
		return empleadoRepository.getAllFechasAndTipo(fechaInicio, fechaFinal, tipoVacunacion);
	}

	@Override
	@Transactional(readOnly = true)
	public List<EmpleadoFilterDto> findAllFechasAndTipoAndEstado(Date fechaInicio, Date fechaFinal,
			String tipoVacunacion, String estadoVacunacion) {
		return empleadoRepository.findAllFechasAndTipoAndEstado(fechaInicio, fechaFinal, tipoVacunacion, estadoVacunacion);
	}

	@Override
	@Transactional(readOnly = true)
	public List<EmpleadoFilterDto> findAllEstVacunacion(String estadoVacunacion) {
		return empleadoRepository.getAllEstado(estadoVacunacion);
	}

	@Override
	@Transactional(readOnly = true)
	public List<EmpleadoFilterDto> findAllTipoVacunacion(String tipoVacunacion) {
		return empleadoRepository.getAllTipo(tipoVacunacion);
	}

	@Override
	@Transactional(readOnly = true)
	public List<EmpleadoFilterDto> findAllTipoAndEstado(String tipoVacunacion, String estadoVacunacion) {
		return empleadoRepository.findAllTipoAndEstado(tipoVacunacion, estadoVacunacion);
	}

}
