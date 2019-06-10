/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.PessoaDAO;
import br.edu.ifsul.modelo.Pessoa;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

/**
 *
 * @author 201613260113
 */
@ManagedBean(name = "controleLogin")
@SessionScoped
public class ControleLogin implements Serializable {

    private PessoaDAO dao = new PessoaDAO();
    private Pessoa usuario = new Pessoa();

    private static Logger logger = Logger.getLogger(ControleLogin.class);

    public String listar() {
        return "/login/login?faces-redirect=true";
    }
    
    public Pessoa getPessoa() {
        return (Pessoa) SessionContext.getInstance().getUsuarioLogado();
    }

    public String doLogin() {
            logger.info("Tentando logar com usuário " + usuario);
            
            usuario = dao.getUsuario(usuario.getLogin(), usuario.getSenha());

            if (usuario == null) {
                usuario = new Pessoa();
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário não encontrado!",
                                "Erro no Login!"));
                return null;
            }  else {
                logger.info("Login efetuado com sucesso");
                SessionContext.getInstance().setAttribute("usuarioLogado", usuario);
                return "/index.xhtml";
            }
    }

    public String doLogout() {
        logger.info("Fazendo logout com usuário "
                + SessionContext.getInstance().getUsuarioLogado().getLogin());
        SessionContext.getInstance().encerrarSessao();
        return "/login/login.xhtml?faces-redirect=true";
    }

//    public String envia() {
//
//        usuario = dao.getUsuario(usuario.getLogin(), usuario.getSenha());
//        if (usuario == null) {
//            usuario = new Pessoa();
//            FacesContext.getCurrentInstance().addMessage(
//                    null,
//                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário não encontrado!",
//                            "Erro no Login!"));
//            return null;
//        } else {
//
//            return "/index.xhtml";
//        }
//
//    }

    public Pessoa getUsuario() {
        return usuario;
    }

    public void setUsuario(Pessoa usuario) {
        this.usuario = usuario;
    }
}
