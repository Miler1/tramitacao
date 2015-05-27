package br.ufla.lemaf.tramitacao.secure;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SecurityFilter implements Filter {

	private static final String CONFIG_FILE = "security.properties";
	
	private final Log log =  LogFactory.getLog(getClass());
	
	private Properties configurations;
	
	private ExternalServiceSecurity externalServiceSecurity;
	
	public void init(FilterConfig filterConfig) throws ServletException {
		
		// Carrega configuracoes
		try {
			configurations = new Properties();
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(CONFIG_FILE);
			configurations.load(inputStream);
			externalServiceSecurity = new ExternalServiceSecurity(configurations);
			
			log.info("Carregado filtro de segurança");
		} catch (IOException e) {
			
			throw new RuntimeException(e);
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		
		externalServiceSecurity.validateAdress(request);
		
		chain.doFilter(request, response);
	}

	public void destroy() {
		
	}
}
