package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Orientador;
import java.io.Serializable;
import javax.ejb.Stateful;


@Stateful
public class OrientadorDAO<T> extends DAOGenerico<Orientador> implements Serializable {

    public OrientadorDAO() {
        super();
        super.setClassePersistente(Orientador.class);
        super.setOrdem("nome");        
    }
 
    @Override
    public Orientador getObjectById(Integer id) throws Exception {
        Orientador obj = (Orientador) super.getEm().find(super.getClassePersistente(), id);
        obj.getPublicacoes().size();
        return obj;
    }       
    
}
