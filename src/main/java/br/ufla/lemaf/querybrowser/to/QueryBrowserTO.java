package br.ufla.lemaf.querybrowser.to;

import java.io.Serializable;
import java.util.Collection;

public class QueryBrowserTO implements Serializable {
	
	private static final long serialVersionUID = 6933875739782890088L;
	
	private String sql;
	private Collection<Object> result;
	private String message;
	
	public QueryBrowserTO() {
		
	}
	
	public QueryBrowserTO(String sql, Collection<Object> result) {
		this.sql = sql;
		this.result = result;
		
		if (result != null && !result.isEmpty())
			this.message = result.size() + " registro(s) retornado(s)";
	}

	public QueryBrowserTO(String sql, String message) {
		this.sql = sql;
		this.message = message;
	}
	
	public String getSql() {
		return sql;
	}
	
	public void setSql(String sql) {
		this.sql = sql;
	}

	public Collection<Object> getResult() {
		return result;
	}

	public void setResult(Collection<Object> result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
