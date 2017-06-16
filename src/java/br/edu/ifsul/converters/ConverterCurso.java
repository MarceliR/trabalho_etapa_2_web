
package br.edu.ifsul.converters;

import br.edu.ifsul.converters.*;
import br.edu.ifsul.modelo.Curso;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@FacesConverter(value = "converterCurso")
public class ConverterCurso implements Converter, Serializable {
    
    @PersistenceContext(unitName = "trabalho_etapa_2_webPU")
    private EntityManager em;    

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.equals("Selecione um registro")){
            return null;
        }
        return em.find(Curso.class, string);
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null){
            return null;
        }
        Curso obj = (Curso) o;
        return obj.getNome();
    }
    

}
