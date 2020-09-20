package br.com.asoft.usermanagementinterface.model;

import javax.persistence.Entity;

import br.com.asoft.usermanagementinterface.model.generics.CadastroSubModel;
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
public class Local extends CadastroSubModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5267584870949856772L;

	private String identificacao;
	private String observacao;

}