package br.com.fabricadeprogramador.filter;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// Dispatcher types informa os tipos de filtros. 
@WebFilter(dispatcherTypes={DispatcherType.REQUEST},urlPatterns="/*")
public class FiltroAutenticacao implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// O ServletRequest e ServletResponse são genéricos, para usar alguns métodos é preciso
		// fazer um casting para HttpServletRequest e HttpServletResponse que são métodos mais específicos.
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		String uri = httpRequest.getRequestURI();
		HttpSession sessao = httpRequest.getSession(false);
		
		
		if ( sessao!=null || uri.lastIndexOf("login.html") !=-1 || uri.lastIndexOf("autenticador.do")!=-1 ) {
			
			chain.doFilter(request, response);
			
		} else {
			
			httpResponse.sendRedirect("login.html");
			
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
}
