package br.ufla.lemaf.querybrowser.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufla.lemaf.querybrowser.repository.QueryBrowserRepository;
import br.ufla.lemaf.querybrowser.to.QueryBrowserTO;
import br.ufla.lemaf.querybrowser.util.QueryBrowserUtil;

@Service
public class QueryBrowserService {

	private static final String ROLE = "QueryBrowserAdmin";

	@Autowired
	private QueryBrowserRepository queryBrowserRepository;
	
	public QueryBrowserTO executeSelect(QueryBrowserTO to) {
		
		Collection<Object> result = null;
		String sql = "";
		
		try {
			
			this.validateExecuteSelect(to);
			
			sql = to.getSql().replaceAll(";", "").trim();
			
			if (sql == null || sql.isEmpty())
				throw new IllegalStateException("SQL nula ou vazia!");

			result = this.queryBrowserRepository.executeSelect(sql);
			
		} catch (Exception e) {

			return new QueryBrowserTO(sql, QueryBrowserUtil.getMessageRootExceptionCause(e));
		}
		
		return new QueryBrowserTO(sql, result);
	}

	@Transactional
	public QueryBrowserTO executeDML(QueryBrowserTO to) {

		String results = "";
		String sql = "";

		try {
			
			this.validateExecuteDML(to);
	
			sql = to.getSql();

			String[] sqls = sql.split(";");
	
			if (sqls == null || sqls.length <= 0)
				return new QueryBrowserTO(sql, "SQL não encontrada!");
	

			for (String sql_ : sqls) {
				
				sql_ = sql_.trim();
				
				if (sql_ == null || sql_.isEmpty())
					throw new IllegalStateException("SQL nula ou vazia!");
				
				Integer result = this.queryBrowserRepository.executeDML(sql_);
				
				results += result + " registro(s) modificados(s)</br>";
			}

			return new QueryBrowserTO(sql, results);
		
		} catch (Exception e) {

			results += QueryBrowserUtil.getMessageRootExceptionCause(e);
			
			throw new IllegalStateException(results);
		}
	}
	
	private void validateExecuteSelect(QueryBrowserTO to) {
		
		if (!QueryBrowserUtil.userHasHole(ROLE))
			throw new IllegalStateException("Você não tem permissão para executar essa operação");
		
		if (to == null)
			throw new IllegalStateException("QueryBrowserTO nulo ou vazio!");
		
		String sql = to.getSql();

		if (sql == null || sql.isEmpty())
			throw new IllegalStateException("SQL nula ou vazia!");
		
		sql = sql.toLowerCase();
		
		if (sql.contains("insert into "))
			throw new IllegalStateException("Comando 'INSERT' inválido para essa operação!");
		
		if (sql.contains("update "))
			throw new IllegalStateException("Comando 'UPDATE' inválido para essa operação!");
		
		if (sql.contains("delete from "))
			throw new IllegalStateException("Comando 'DELETE' inválido para essa operação!");
	}
	
	@Transactional
	private void validateExecuteDML(QueryBrowserTO to) {
		
		if (!QueryBrowserUtil.userHasHole(ROLE))
			throw new IllegalStateException("Você não tem permissão para executar essa operação");

		if (to == null)
			throw new IllegalStateException("QueryBrowserTO nulo ou vazio!");

		if (to.getSql() == null || to.getSql().isEmpty())
			throw new IllegalStateException("SQL nula ou vazia!");
	}
	
	
}
