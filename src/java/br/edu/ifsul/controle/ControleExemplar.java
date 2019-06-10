/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ExemplarDAO;
import br.edu.ifsul.dao.LivroDAO;
import br.edu.ifsul.modelo.Exemplar;
import br.edu.ifsul.modelo.Livro;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author 201613260113
 */
@ManagedBean(name = "controleExemplar")
@ViewScoped
//@RequestScoped
public class ControleExemplar implements Serializable {

    private ExemplarDAO<Exemplar> dao;
    private LivroDAO<Livro> daoLivro;
    private Exemplar objeto;

    public ControleExemplar() {
        dao = new ExemplarDAO<>();
        daoLivro = new LivroDAO<>();
    }

    public String listar() {
        return "/privado/exemplar/listar?faces-redirect=true";
    }

    public void novo() {
        setObjeto(new Exemplar());
    }

    public void salvar() {
        boolean persistiu;
        if (getObjeto().getId() == null) {
            persistiu = dao.persist(getObjeto());
        } else {
            persistiu = dao.merge(getObjeto());
        }
        if (persistiu) {
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public void editar(Integer id) {
        setObjeto(dao.localizar(id));
    }
    
    public void cudequemtalendo() {
        dao.getListaObjetos();
    }
    
    

    public void remover(Integer id) {
        setObjeto(dao.localizar(id));
        if (dao.remover(getObjeto())) {
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public ExemplarDAO getDao() {
        return dao;
    }

    public void setDao(ExemplarDAO dao) {
        this.dao = dao;
    }

    public Exemplar getObjeto() {
        return objeto;
    }

    public void setObjeto(Exemplar objeto) {
        this.objeto = objeto;
    }

    /**
     * @return the daoGenero
     */
    public LivroDAO<Livro> getDaoLivro() {
        return daoLivro;
    }

    /**
     * @param daoGenero the daoGenero to set
     */
    public void setDaoLivro(LivroDAO<Livro> daoLivro) {
        this.daoLivro = daoLivro;
    }

}
