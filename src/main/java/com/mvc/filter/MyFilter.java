package com.mvc.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.core.annotation.Order;

@Order
public class MyFilter implements Filter {  
	public void destroy() {}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("filter-1");
		chain.doFilter(request, response);//다음 필터나 DispatcherServlet으로 gogo
		System.out.println("filter-2");
	}
	
	public void init(FilterConfig fConfig) throws ServletException {}
}
