package org.etix.domain.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class ListUtils {

    /**
     * @param <T>
     * @param elements
     * @param element
     */
    public static <T> void addItem(List<T> elements, T element) {
        if (!(elements instanceof ArrayList)) {
            elements = new ArrayList<>(elements); // Crée une copie mutable si nécessaire
        }
        int index = elements.indexOf(element);
        if (index >= 0) {
            elements.set(index, element);
        } else {
            elements.add(element);
        }
    }


    /**
     * @param <T>
     * @param elements
     * @param element
     */
    public static <T> void removeItem(List<T> elements, T element) {
        if (!(elements instanceof ArrayList)) {
            elements = new ArrayList<>(elements); // Crée une copie mutable si nécessaire
        }
        elements.removeIf(item -> Objects.equals(item, element));
    }


    public class GenericManager {

        // Méthode pour rattacher plusieurs éléments à une collection
        public static <T> void rattacher(Collection<T> collection, T... elements) {
            if (collection != null && elements != null) {
                for (T element : elements) {
                    collection.add(element);
                    System.out.println("Élément " + element + " ajouté à la collection.");
                }
            } else {
                System.out.println("La collection ou les éléments sont nuls.");
            }
        }

        // Méthode pour détacher plusieurs éléments d'une collection
        public static <T> void detacher(Collection<T> collection, T... elements) {
            if (collection != null && elements != null) {
                for (T element : elements) {
                    if (collection.remove(element)) {
                        System.out.println("Élément " + element + " retiré de la collection.");
                    } else {
                        System.out.println("L'élément " + element + " n'est pas trouvé dans la collection.");
                    }
                }
            } else {
                System.out.println("La collection ou les éléments sont nuls.");
            }
        }

    }
}
