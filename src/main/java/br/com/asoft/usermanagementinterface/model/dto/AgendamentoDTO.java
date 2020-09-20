package br.com.asoft.usermanagementinterface.model.dto;

import java.util.Calendar;

import br.com.asoft.usermanagementinterface.model.Agendamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AgendamentoDTO {
	private Integer id;
	private Calendar dataAgendamento;
	private String nomeFuncionario;
	private Integer idFuncionario;
	private Integer local;
	private Integer area;
	private Integer subArea;
	private String about;

	public AgendamentoDTO(Agendamento agendamento) {

		if (null == agendamento) {
			return;
		}
		this.id = agendamento.getId();
		this.dataAgendamento = agendamento.getDataAgendamento();
		this.nomeFuncionario = agendamento.getUsuario().getFirstName();
		this.idFuncionario = agendamento.getUsuario().getId();
		this.local = agendamento.getLocal().getId();
		this.area = agendamento.getArea().getId();
		this.subArea = agendamento.getSubArea().getId();
		this.about = agendamento.getAbout();
	}
}
