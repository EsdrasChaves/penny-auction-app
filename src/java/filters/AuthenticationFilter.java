/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
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
import util.FacesUtil;
import util.SessionUtil;

/**
 *
 * @author esdra
 */
@WebFilter(filterName = "AuthenticationFilter", urlPatterns = {"/leilao/*"})
public class AuthenticationFilter implements Filter {
    
    private boolean isUserLogged;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
	HttpSession session = req.getSession();
        
        if(session == null)
            isUserLogged = false;
        else
            isUserLogged = (session.getAttribute("currentUser") != null && !session.getAttribute("currentUser").equals(""));
        
        if(isUserLogged 
                || (req.getRequestURI().endsWith("home.xhtml"))
                || (req.getRequestURI().endsWith("login.xhtml"))
                || (req.getRequestURI().endsWith("register.xhtml")))
        {
            chain.doFilter(request, response);
        } else {
            redirect("login.xhtml", response);
        }
        
    }

    @Override
    public void destroy() {
    }
    
    private void redirect(String url, ServletResponse response)
			throws IOException {
        HttpServletResponse res = (HttpServletResponse) response;
	res.sendRedirect(url);
    }
    
}
