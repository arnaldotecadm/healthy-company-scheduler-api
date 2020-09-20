package br.com.asoft.usermanagementinterface.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;


import br.com.asoft.usermanagementinterface.model.generics.CadastroModel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Agendamento extends CadastroModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5267584870949856772L;

	@NotNull
	@Temporal(TemporalType.DATE)
	private Calendar dataAgendamento;

	@NotNull
	@ManyToOne
	private Usuario usuario;

	@NotNull
	@ManyToOne
	private Local local;

	@NotNull
	@ManyToOne
	private Area area;

	@NotNull
	@ManyToOne
	private SubArea subArea;

	private String about;

}