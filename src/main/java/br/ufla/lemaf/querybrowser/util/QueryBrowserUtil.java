package br.ufla.lemaf.querybrowser.util;

//import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

//import org.apache.catalina.realm.GenericPrincipal;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


public class QueryBrowserUtil {

	public static String getMessageRootExceptionCause(Throwable throwable) {
		
		return (throwable.getCause() == null)
				? throwable.getClass().getSimpleName() + ": " + throwable.getMessage()
				: QueryBrowserUtil.getMessageRootExceptionCause(throwable.getCause());
		
	}
	
	public static boolean userHasHole(String role) {
		return QueryBrowserUtil.userHasHole(role, QueryBrowserUtil.getCurrentRequest());
	}
	
	public static boolean userHasHole(String role, HttpServletRequest request) {
		
//		if (role == null || role.isEmpty() || request == null)
//			return false;
//		
//		if (isUserAuthenticated(request)) {
//			
//			String[] roles = getRolesFromPrincipal(request.getUserPrincipal());
//			
//			if (roles == null || roles.length <= 0)
//				return false;
//			
//			for (String userRole : roles) {
//				
//				if (userRole.contains(role))
//					return true;
//			}
//		}
		
		return true; // TODO Tramitacao nao possui autenticacao
	}

	private static HttpServletRequest getCurrentRequest() {

		return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
			.getRequest();
	}
	
	private static boolean isUserAuthenticated(HttpServletRequest request) {
		
		if (request != null && request.getUserPrincipal() != null && request.getUserPrincipal().getName() != null)
			
			return true;
		
		return false;
	}

//	private static String [] getRolesFromPrincipal(Principal principal) {
//		
//		if (principal instanceof GenericPrincipal) {
//			
//			return ((GenericPrincipal)principal).getRoles();
//		}
//		
//		return null;
//	}

}
