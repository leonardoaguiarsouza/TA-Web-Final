package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Genero;
import java.io.Serializable;

/**
 *
 * @author 201613260113
 */
public class GeneroDAO<TIPO> extends DAOGenerico<Genero> implements Serializable {

    public GeneroDAO() {
        super();
        classePersistente = Genero.class;
        ordem = "nome";
    }

}
