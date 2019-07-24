package com.dndy.filter;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.HiddenHttpMethodFilter;

/**
 *  请求过滤器
 */
@Order(1)
@WebFilter(filterName = "HeaderHttpMethodFilter", urlPatterns = "/*")
public class HeaderHttpMethodFilter extends HiddenHttpMethodFilter {

	/** Default method parameter: {@code _method} */
	public static final String DEFAULT_METHOD_PARAM = "method";

	private String methodParam = DEFAULT_METHOD_PARAM;


	/**
	 * Set the parameter name to look for HTTP methods.
	 * @see #DEFAULT_METHOD_PARAM
	 */
	public void setMethodParam(String methodParam) {
		Assert.hasText(methodParam, "'methodParam' must not be empty");
		this.methodParam = methodParam;
	}

	/**
	 * 如果是post请求，且request的header有_method,则以_method请求的方式请求
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String paramValue = request.getHeader(this.methodParam);
		if ("POST".equals(request.getMethod()) && StringUtils.hasLength(paramValue)) {
			String method = paramValue.toUpperCase(Locale.ENGLISH);
			HttpServletRequest wrapper = new HttpMethodRequestWrapper(request, method);
			filterChain.doFilter(wrapper, response);
		}
		else {
			filterChain.doFilter(request, response);
		}
	}


	/**
	 * Simple {@link HttpServletRequest} wrapper that returns the supplied method for
	 * {@link HttpServletRequest#getMethod()}.
	 */
	private static class HttpMethodRequestWrapper extends HttpServletRequestWrapper {

		private final String method;

		public HttpMethodRequestWrapper(HttpServletRequest request, String method) {
			super(request);
			this.method = method;
		}

		@Override
		public String getMethod() {
			return this.method;
		}
	}


}
