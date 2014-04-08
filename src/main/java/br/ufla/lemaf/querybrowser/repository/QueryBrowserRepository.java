package br.ufla.lemaf.querybrowser.repository;

import java.util.Collection;

import org.springframework.transaction.annotation.Transactional;

public interface QueryBrowserRepository {

	Collection<Object> executeSelect(String sql);
	
	@Transactional
	Integer executeDML(String sql);
}
