package br.com.asoft.usermanagementinterface.model.generics;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public abstract class CadastroSubModel extends CadastroModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9074771974089736426L;

	private String nome;
	private String descricao;

}