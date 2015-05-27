package br.ufla.lemaf.tramitacao.secure;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

import javax.servlet.ServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ExternalServiceSecurity {

	private final Log log =  LogFactory.getLog(getClass());
	
	private static final String IPS_CONFIG = "secure.public.adress";
	
	private boolean secureEnabled;
	private String [] defaultIps;
	
	private Properties configuration;
	
	public ExternalServiceSecurity(Properties configuration) {
		
		this.configuration = configuration;
		this.secureEnabled = Boolean.parseBoolean(configuration.getProperty("secure.public.enabled"));
		this.defaultIps = getConfigArray(IPS_CONFIG);
	}
	
	public void validateAdress(ServletRequest request) {
		
		if (!this.secureEnabled)
			return;
		
		//cria uma string que recebe o IP da requisicao, e outra recebe a lista de IPs validos
		String remoteAdress = request.getRemoteAddr();
		String [] list = defaultIps;
		
		// Se nao estiver configurada a seguranca por IP
		if (list == null || remoteAdress == null)
			unauthorized(request);
		

		InetAddress addr = null;
		InetAddress remoteAddr = null;
		
		Boolean isPermitted = false;
		
		try {
			
			remoteAddr = InetAddress.getByName(remoteAdress);				
			
			for (int i = 0; i < list.length; i++) {

				InetAddressComparator addressComparator = new InetAddressComparator();

				addr = InetAddress.getByName(list[i]);
				
				//caso os IPS sejam iguais, a requisicao tem permissao
				if (addressComparator.compare(remoteAddr, addr) == 0)
					isPermitted = true;
			}
			

		} catch (UnknownHostException e) {
			unauthorized(request);
		}
		
		//caso nao tenha permissao, sera requisitado a autenticacao do usuario
		if (!isPermitted)
			unauthorized(request);
	}

	private void unauthorized(ServletRequest request) {
		
		log.info("UNAUTHORIZED: Não habilitado acesso ao IP " + request.getRemoteAddr());
		throw new RuntimeException("Unauthorized");
	}
	
	private String [] getConfigArray(String config) {
		
		String adressList = configuration.getProperty(config);
		
		if (adressList != null && !adressList.trim().isEmpty())
			return adressList.split(",");
		
		return null;
	}
}
