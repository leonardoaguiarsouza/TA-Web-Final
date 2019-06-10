/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.PessoaDAO;
import br.edu.ifsul.dao.EmprestimoDAO;
import br.edu.ifsul.dao.ExemplarDAO;
import br.edu.ifsul.modelo.Pessoa;
import br.edu.ifsul.modelo.Emprestimo;
import br.edu.ifsul.modelo.Exemplar;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author 201613260113
 */
@ManagedBean(name = "controleEmprestimo")
@ViewScoped
public class ControleEmprestimo implements Serializable {

    private EmprestimoDAO<Emprestimo> dao;
    private PessoaDAO<Pessoa> daoPessoa;
    private ExemplarDAO<Exemplar> daoExemplar;
    private Emprestimo objeto;

    public ControleEmprestimo() {
        dao = new EmprestimoDAO<>();
        daoPessoa = new PessoaDAO<>();
        daoExemplar = new ExemplarDAO<>();
    }

    public String listar() {
        return "/privado/emprestimo/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Emprestimo();
    }

    public void salvar() {
        boolean persistiu;
        if (objeto.getId() == null) {
            persistiu = dao.persist(objeto);
        } else {
            persistiu = dao.merge(objeto);
        }
        if (persistiu) {
            Util.mensagemInformacao(dao.getMensagem());
            dao.refresh();
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public void editar(Integer id) {
        objeto = dao.localizar(id);
    }

    public void remover(Integer id) {
        objeto = dao.localizar(id);
        if (dao.remover(objeto)) {
            Util.mensagemInformacao(dao.getMensagem());
            dao.refresh();
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public EmprestimoDAO getDao() {
        return dao;
    }

    public void setDao(EmprestimoDAO dao) {
        this.dao = dao;
    }

    public Emprestimo getObjeto() {
        return objeto;
    }

    public void setObjeto(Emprestimo objeto) {
        this.objeto = objeto;
    }

    /**
     * @return the daoPessoa
     */
    public PessoaDAO<Pessoa> getDaoPessoa() {
        return daoPessoa;
    }

    /**
     * @param daoPessoa the daoPessoa to set
     */
    public void setDaoPessoa(PessoaDAO<Pessoa> daoPessoa) {
        this.daoPessoa = daoPessoa;
    }

    /**
     * @return the daoExemplar
     */
    public ExemplarDAO<Exemplar> getDaoExemplar() {
        return daoExemplar;
    }

    /**
     * @param daoExemplar the daoExemplar to set
     */
    public void setDaoExemplar(ExemplarDAO<Exemplar> daoExemplar) {
        this.daoExemplar = daoExemplar;
    }

}
