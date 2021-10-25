package com.reto.inventario.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="vacuna")
public class Vacuna implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_vacuna")
	private Integer idVacuna;

	private Integer dosis;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_vacunacion")
	private Date fechaVacunacion;

	@Column(name="tipo_vacuna")
	private String tipoVacuna;

	@ManyToOne
	@JoinColumn(name="id_empleado")
	private Empleado empleado;

	public Vacuna() {
	}

	public Integer getIdVacuna() {
		return this.idVacuna;
	}

	public void setIdVacuna(Integer idVacuna) {
		this.idVacuna = idVacuna;
	}

	public Integer getDosis() {
		return this.dosis;
	}

	public void setDosis(Integer dosis) {
		this.dosis = dosis;
	}

	public Date getFechaVacunacion() {
		return this.fechaVacunacion;
	}

	public void setFechaVacunacion(Date fechaVacunacion) {
		this.fechaVacunacion = fechaVacunacion;
	}

	public String getTipoVacuna() {
		return this.tipoVacuna;
	}

	public void setTipoVacuna(String tipoVacuna) {
		this.tipoVacuna = tipoVacuna;
	}

	public Empleado getEmpleado() {
		return this.empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

}