package br.com.asoft.usermanagementinterface.model.generics;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class CadastroModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9074771974089736426L;

	@Include
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;

}