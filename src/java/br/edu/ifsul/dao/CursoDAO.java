package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Curso;
import java.io.Serializable;
import javax.ejb.Stateful;


@Stateful
public class CursoDAO<T> extends DAOGenerico<Curso> implements Serializable {

    public CursoDAO(){
        super();
        super.classePersistente = Curso.class;
    }
    
    public T getObjectById(String id) throws Exception {
        return (T) em.find(classePersistente, id);
    }    
}
