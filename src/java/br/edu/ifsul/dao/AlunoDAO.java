
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Aluno;
import java.io.Serializable;
import javax.ejb.Stateful;
import javax.persistence.Query;


@Stateful
public class AlunoDAO<T> extends DAOGenerico<Aluno> implements Serializable {

    public  AlunoDAO(){
        super();
        super.classePersistente = Aluno.class;
    }
    
    @Override
    public Aluno getObjectById(Integer id) throws Exception {
        Aluno obj = (Aluno) em.find(classePersistente, id);
        /// inicializando a lista para não lazy inicialization exception
        obj.getCursam().size();
        return obj;
    }    
    
    public Aluno localizaPorNomeAluno(String usuario){
        Query query = em.createQuery("from Aluno where upper(usuario) = :usuario");
        query.setParameter("usuario", usuario.toUpperCase());
        Aluno obj = (Aluno) query.getSingleResult();
        obj.getCursam().size();
        return obj;
    }
            
}
