package ua.footballdata.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * For details:
 * https://www.mkyong.com/spring-boot/spring-rest-error-handling-example/
 * 
 * @author AnGo
 *
 */

@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class AppBasicErrorController extends AbstractErrorController {

	public AppBasicErrorController(ErrorAttributes errorAttributes) {
		super(errorAttributes);
	}

	@RequestMapping
	public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
		Map<String, Object> body = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.ALL));
		HttpStatus status = getStatus(request);
		return new ResponseEntity<>(body, status);
	}

	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return null;
		// return "/error";
	}

	/**
	 * Determine if the stacktrace attribute should be included.
	 * 
	 * @param request  the source request
	 * @param produces the media type produced (or {@code MediaType.ALL})
	 * @return if the stacktrace attribute should be included
	 */
	protected boolean isIncludeStackTrace(HttpServletRequest request, MediaType produces) {
		/*
		 * IncludeStacktrace include = getErrorProperties().getIncludeStacktrace(); if
		 * (include == IncludeStacktrace.ALWAYS) { return true; } if (include ==
		 * IncludeStacktrace.ON_TRACE_PARAM) { return getTraceParameter(request); }
		 * return false;
		 */
		return true;
	}

}
