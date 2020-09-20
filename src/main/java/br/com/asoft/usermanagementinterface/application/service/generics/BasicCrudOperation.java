package br.com.asoft.usermanagementinterface.application.service.generics;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

public interface BasicCrudOperation<T> {

	public T getById(Integer id);

	public List<T> getAll();

	public void removeById(@PathVariable("id") Integer id);
}
