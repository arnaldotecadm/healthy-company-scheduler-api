package br.com.asoft.usermanagementinterface.repository;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.asoft.usermanagementinterface.model.Agendamento;
import br.com.asoft.usermanagementinterface.model.Usuario;

public interface AgendamentoRepository extends CrudRepository<Agendamento, Integer> {

	Agendamento getByUsuarioAndDataAgendamento(Usuario usuario, Calendar dataAgendamento);

	List<Agendamento> getByDataAgendamento(Calendar dataAgendamento);
}