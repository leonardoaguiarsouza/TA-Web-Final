package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Pessoa;
import java.io.Serializable;
import javax.persistence.NoResultException;

/**
 *
 * @author 201613260113
 */
public class PessoaDAO<TIPO> extends DAOGenerico<Pessoa> implements Serializable {

    public PessoaDAO() {
        super();
        classePersistente = Pessoa.class;
        ordem = "nome";
    }
    
    public Pessoa getUsuario(String nomeUsuario, String senha) {
 
      try {
        Pessoa usuario = (Pessoa) em
         .createQuery("SELECT u from Pessoa u where u.login = :nomeUsuario and u.senha = :senha")
         .setParameter("nomeUsuario", nomeUsuario)
         .setParameter("senha", senha).getSingleResult();
 
        return usuario;
      } catch (NoResultException e) {
            return null;
      }
    }

}
