package br.ufla.lemaf.tramitacao.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

public class DateTimeDeserializer extends JsonDeserializer<Date> {

	public static final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";

	private SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);

	@Override
	public Date deserialize(JsonParser parser, DeserializationContext context)
			throws IOException, JsonProcessingException {

		try {

			return formatter.parse(parser.getText());

		} catch (ParseException e) {

			e.printStackTrace();
			return null;
		}
	}

}
