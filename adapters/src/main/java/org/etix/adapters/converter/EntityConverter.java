package org.etix.adapters.converter;


import jakarta.annotation.ManagedBean;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UISelectItems;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

/**
 * @author Luc
 */
@ManagedBean
@ViewScoped
@Named(value = "entityConverter")
public class EntityConverter implements Serializable, Converter<Object> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Rechercher l'index du composants SelectItems
     *
     * @param list
     * @return
     */
    private int getIndex(List<UIComponent> list) {
        int index = 0;
        int position = -1;
        for (UIComponent component : list) {

            if (component.getClass() == UISelectItems.class) {
                position = index;
                break;
            }

            index++;
        }

        return position;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        if (value == null || value.isEmpty()) {
            return null;
        }

        try {
            // On recupere les composants enfants du composant SelectOneMenu
            List<UIComponent> components = component.getChildren();
            // On recupere la valeur du composant SelectItems
            Object selectItemsValue = components.get(this.getIndex(components)).getAttributes().get("value");

            /**
             * Pour chaque SelectItem, on recupere la valeur On détermine la
             * classe de la valeur On récupère les attributs de la classe On
             * vérifie que l'ID de classe correspond à la valeur "value" passée
             * en paramètre
             */
            for (Object valueSelect : (List<?>) selectItemsValue) {
                try {
                    Class<?> classe = Class.forName(valueSelect.getClass().getName());
                    Field[] declaredFields = classe.getDeclaredFields();

                    for (Field declaredField : declaredFields) {

                        if (declaredField.getAnnotation(Id.class) != null) {

                            try {
                                declaredField.setAccessible(true);

                                String id = declaredField.get(valueSelect).toString();

                                if (id.equals(value)) {
                                    return valueSelect;
                                }
                            } catch (IllegalArgumentException | IllegalAccessException e) {
                                e.printStackTrace();
                            }

                        }
                    }

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

            return null;
        } catch (NumberFormatException e) {
            throw new ConverterException(new FacesMessage(String.format("%s l'ID  est invalide.", value)), e);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if (!Objects.isNull(value)) {
            Class<? extends Object> classe = value.getClass();
            try {
                Class<?> test = Class.forName(classe.getName());
                Field[] declaredFields = test.getDeclaredFields();
                for (Field declaredField : declaredFields) {
                    if (declaredField.getAnnotation(Id.class) != null) {

                        try {
                            declaredField.setAccessible(true);
                            return declaredField.get(value).toString();
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }

                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        return "";
    }

}
