package br.ufla.lemaf.querybrowser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufla.lemaf.querybrowser.service.QueryBrowserService;
import br.ufla.lemaf.querybrowser.to.QueryBrowserTO;
import br.ufla.lemaf.querybrowser.util.QueryBrowserUtil;

@Controller
@RequestMapping( { "/query/**" } )
public class QueryBrowserController {

	@Autowired
	private QueryBrowserService queryBrowserService;

	@RequestMapping( value = "/query/select", method = RequestMethod.POST )
	public QueryBrowserTO executeSelect( @RequestBody QueryBrowserTO to ) {

		return this.queryBrowserService.executeSelect(to);
	}

	@RequestMapping( value = "/query/dml", method = RequestMethod.POST )
	public QueryBrowserTO executeDML( @RequestBody QueryBrowserTO to ) {

		try {
			
			return this.queryBrowserService.executeDML(to);
			
		} catch (Exception e) {
			
			String message = QueryBrowserUtil.getMessageRootExceptionCause(e);

			if (to.getMessage() == null)
				to.setMessage(message);
			
			else
				to.setMessage( to.getMessage() + "</br>" + message );
		}
		
		return to;
	}
}
