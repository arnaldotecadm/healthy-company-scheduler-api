package br.com.asoft.usermanagementinterface.application.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum EnumUpdateDetail {
	BUG("Correção de bugs"),
	HOTFIX("Correção de Bugs em Produção ou que exijam correção em tempo real"),
	FEATURE("Novas funcionalidades"),
	ENHANCEMENT("Aprimoramentos/Melhoras de performance/utilização");
	
	@Getter
	private final String label;

}
