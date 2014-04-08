package br.ufla.lemaf.tramitacao.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public class DateTimeSerializer extends JsonSerializer<Date> {

	public static final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";

	private SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);

	@Override
	public void serialize(Date date, JsonGenerator generator,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {

		generator.writeString(formatter.format(date));
	}

}
