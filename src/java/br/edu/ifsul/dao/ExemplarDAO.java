package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Exemplar;
import java.io.Serializable;

/**
 *
 * @author 201613260113
 */
public class ExemplarDAO<TIPO> extends DAOGenerico<Exemplar> implements Serializable {

    public ExemplarDAO() {
        super();
        classePersistente = Exemplar.class;
        ordem = "id";
    }

}
