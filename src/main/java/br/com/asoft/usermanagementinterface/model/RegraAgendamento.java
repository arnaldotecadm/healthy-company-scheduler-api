package br.com.asoft.usermanagementinterface.model;

import javax.persistence.Entity;

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
public class RegraAgendamento extends CadastroModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5267584870949856772L;

	private Integer limiteDiasPorSemana;
	private Integer limitePessoasPorLocal;
	private Integer limitePessoasPorArea;
	private Integer limitePessoasPorSubArea;
	private Integer limitePessoasPorDia;

}