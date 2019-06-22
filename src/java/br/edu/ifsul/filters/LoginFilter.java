package br.edu.ifsul.filters;

import br.edu.ifsul.modelo.Pessoa;
import java.io.IOException;
  
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
  
@WebFilter("/privado/*")
public class LoginFilter implements Filter {
  
         @Override
         public void destroy() {
                   // TODO Auto-generated method stub
  
         }
  
         @Override
         public void doFilter(ServletRequest request, ServletResponse response,
                            FilterChain chain) throws IOException, ServletException {
             Pessoa user = null;
             HttpSession sess = ((HttpServletRequest) request).getSession(false);
              
             if (sess != null){
                   user = (Pessoa) sess.getAttribute("usuarioLogado");
             }      
  
                   if (user == null) {
                            String contextPath = ((HttpServletRequest) request)
                                               .getContextPath();
                            ((HttpServletResponse) response).sendRedirect(contextPath
                                               + "/login/login.xhtml");
                   } else {
                            chain.doFilter(request, response);
                   }
  
         }
  
         @Override
         public void init(FilterConfig arg0) throws ServletException {
                   // TODO Auto-generated method stub
  
         }
  
}