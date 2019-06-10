package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Emprestimo;
import java.io.Serializable;

/**
 *
 * @author 201613260113
 */
public class EmprestimoDAO<TIPO> extends DAOGenerico<Emprestimo> implements Serializable {

    public EmprestimoDAO() {
        super();
        classePersistente = Emprestimo.class;
        ordem = "id";
    }

}
