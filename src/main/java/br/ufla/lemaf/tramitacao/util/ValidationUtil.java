package br.ufla.lemaf.tramitacao.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

import br.ufla.lemaf.tramitacao.exception.ValidationException;

public class ValidationUtil {

	public static void notNull(Object... objects) {
		for (Object object : objects)
			notNull(object, "Campos obrigatórios não preenchidos.");
	}
	
	public static void notNull(String message, Object... objects) {
		for (Object object : objects)
			notNull(object, message);
	}
	
	public static void notNull(Object object, String message) {
		if (object == null)
			throw new ValidationException(message);
	}
	
	public static void isTrue(boolean bool, String message) {
		if (!bool)
			throw new ValidationException(message);
	}
	
	public static void notNullNotEmpty(@SuppressWarnings("rawtypes") Collection collection, String message) {
		if (collection == null || collection.isEmpty())
			throw new ValidationException(message);
	}
	
	public static void notNullNotEmpty(String string, String message) {
		if (string == null || string.trim().isEmpty())
			throw new ValidationException(message);
	}
	
	public static void idNotNull(Object object, String message) {
		
		notNull(object, message);
		
		@SuppressWarnings("rawtypes")
		Class c = object.getClass();
		
		try {
			Method method = c.getDeclaredMethod("getId");
			Object value = method.invoke(object);
			
			notNull(value, message);
			
			return;
			
		} catch (NoSuchMethodException e) {
		} catch (InvocationTargetException e) {
		} catch (IllegalAccessException e) {
		}
		
		throw new ValidationException(message);
	}
}
