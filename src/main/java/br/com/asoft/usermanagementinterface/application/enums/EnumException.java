package br.com.asoft.usermanagementinterface.application.enums;

import org.springframework.http.HttpStatus;

public enum EnumException {

	UNKNOWN_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Unknown error found."),
	FORBIDDEN(HttpStatus.FORBIDDEN, "Operação não permitida."),
	ITEM_CREATED(HttpStatus.CREATED, "Item inserido com sucesso."),
	CLIENTE_NAO_ENCONTRADO(HttpStatus.NOT_FOUND, "Cliente informado para a pesquisa não encontrado."),
	LOCAL_NAO_ENCONTRADO(HttpStatus.NOT_FOUND, "Local informado para a pesquisa não encontrado."),
	AREA_NAO_ENCONTRADA(HttpStatus.NOT_FOUND, "Area informado para a pesquisa não encontrada."),
	SUB_AREA_NAO_ENCONTRADA(HttpStatus.NOT_FOUND, "SubArea informado para a pesquisa não encontrada."),
	PUBLIC_KEY_MALFORMED(HttpStatus.NOT_ACCEPTABLE,"Chave publica informada está incorreta"),
	FALHA_VALIDACAO_LIMITE_POR_DIA(HttpStatus.FAILED_DEPENDENCY,"Limite Diário de Funcionários já foi atingido, por favor escolha outra data."),
	
	
	
	SOFTWARE_NAO_ENCONTRADO(HttpStatus.NOT_FOUND, "Software informado para a pesquisa não encontrado."),
	
	
	APPLICATION_IN_MAINTENANCE(HttpStatus.UNAUTHORIZED, "O software se encontra atualmente em Manutenção, tente mais tarde."),
	APPLICATION_INACTIVE(HttpStatus.UNAUTHORIZED, "O software se encontra desativado no momento. Por favor entre em contato com o Suporte."),
	
	NO_UPDATE_INFORMED(HttpStatus.BAD_REQUEST, "Nunhuma lista de updates foi informada."),
	
	RELEASE_TYPE_NOT_FOUND(HttpStatus.BAD_REQUEST, "Tipo de release informado não encontrado."),
	USER_DISABLED(HttpStatus.BAD_REQUEST, "Usuário bloqueado."),
	INVALID_CREDENTIALS(HttpStatus.BAD_REQUEST, "Credenciais informadas incorretamente.");

	private HttpStatus httpStatus;
	private String description;

	EnumException(HttpStatus httpStatus, String description) {
		this.httpStatus = httpStatus;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}
