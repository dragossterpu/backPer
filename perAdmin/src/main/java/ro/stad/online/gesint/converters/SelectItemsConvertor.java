package ro.stad.online.gesint.converters;

import java.util.Iterator;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

/**
 * Componente que permite generar desplegables en formularios html donde las opciones están asociadas a objetos.
 * 
 * @author STAD
 */
@Component("selectConverter")
public class SelectItemsConvertor implements Converter {
    
    /**
     * Factoria de entityManager.
     */
    @PersistenceContext
    private EntityManager entityManagerFactory;
    
    /**
     * Dado el objeto entity, devuelve el valor de su clave primera en cadena de texto.
     * 
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object entity) {
        String value = null;
        if (entity != null && "".equals(entity) == Boolean.FALSE) {
            Object id = entityManagerFactory.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(entity);
            value = id.toString();
        }
        return value;
    }
    
    /**
     * Devuelve el objeto que corresponde al id de la entity recibido como parámetro en el submitedValue del combo.
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submitedValue) {
        Object entity = null;
        List<UIComponent> childrenList = component.getChildren();
        Iterator<UIComponent> iteratorChildren = childrenList.iterator();
        boolean encontrado = false;
        UIComponent child;
        Object item = null;
        while (iteratorChildren.hasNext() && !encontrado) {
            child = iteratorChildren.next();
            if (child instanceof UISelectItems) {
                UISelectItems uiSelectItems = (UISelectItems) child;
                @SuppressWarnings("unchecked")
                List<SelectItem> listaItems = (List<SelectItem>) uiSelectItems.getValue();
                if (listaItems != null) {
                    Iterator<SelectItem> iteratorItems = listaItems.iterator();
                    while (iteratorItems.hasNext() && !encontrado) {
                        item = iteratorItems.next();
                        encontrado = submitedValue.equals(item.toString());
                    }
                }
            }
        }
        if (encontrado) {
            entity = item;
        }
        return entity;
    }
    
}

