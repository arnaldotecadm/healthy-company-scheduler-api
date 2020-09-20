package br.com.asoft.usermanagementinterface.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.asoft.usermanagementinterface.model.Local;

public interface LocalRepository extends CrudRepository<Local, Integer> {

}