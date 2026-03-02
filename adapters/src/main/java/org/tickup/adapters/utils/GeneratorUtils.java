package org.tickup.adapters.utils;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GeneratorUtils {

    private final FileManager fileManager;


    public GeneratorUtils() {
        fileManager = new FileManager();
    }

    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public void constructToDomainAndToEntity(String packageName) throws ClassNotFoundException {
        List<String> packageClasse = getClassNames(packageName);
        for (String packageClass : packageClasse) {
            String filePath = "adapters/src/main/java/" + packageClass.replace(".", "/") + ".java";
            try {
                // Lire le fichier existant
                BufferedReader reader = new BufferedReader(new FileReader(filePath));
                StringBuilder fileContent = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    fileContent.append(line).append("\n");
                }
                reader.close();

                // Ajouter les méthodes avant la dernière accolade fermante
                int lastIndex = fileContent.lastIndexOf("}");
                fileContent.insert(lastIndex, constructToDomain(Class.forName(packageClass), packageClasse) + constructToEntity(Class.forName(packageClass), packageClasse));

                // Écrire le contenu modifié dans le fichier
                BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
                writer.write(fileContent.toString());
                writer.close();

                System.out.println("Les méthodes ont été ajoutées avec succès.");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//            System.out.println(constructToDomain(Class.forName(packageClass), packageClasse));
//            System.out.println(constructToEntity(Class.forName(packageClass), packageClasse));


    }

    /**
     * Construction de la méthode toDomain
     *
     * @param clazz
     * @param packageClasse
     * @return
     */
    public String constructToDomain(Class<?> clazz, List<String> packageClasse) {
        // Récupérer le nom de la classe
        String className = clazz.getSimpleName();
        // Récupérer le nom de la classe sans "Entity"
        String domainClassName = className.replace("Entity", "");
        StringBuilder toDomainMethod = new StringBuilder();

        // Commencer à construire la méthode toDomain
        toDomainMethod.append("public ").append(domainClassName).append(" toDomain() {\n")
                .append("    return new ").append(domainClassName).append(".Builder()\n");

        for (Field field : clazz.getDeclaredFields()) {
            if (!Modifier.isStatic(field.getModifiers())) {
                String fieldName = field.getName();
                if (!packageClasse.contains(field.getType().getName())) {
                    // Champ non-entité, pas de vérification de nullité nécessaire
                    toDomainMethod.append("            .").append(fieldName)
                            .append("(").append(fieldName).append(")\n");
                } else {
                    // Champ de type entité, ajouter une vérification de nullité
                    toDomainMethod.append("            .").append(fieldName).append("(")
                            .append(fieldName).append(" != null ? ").append(fieldName)
                            .append(".toDomain() : null)\n");
                }
            }
        }
        toDomainMethod.append("            .build();\n}\n");

        return toDomainMethod.toString();
    }


    /**
     * Construction de la méthode toEntity
     *
     * @param clazz
     * @param packageClasse
     * @return
     */
    public String constructToEntity(Class<?> clazz, List<String> packageClasse) {
        StringBuilder toEntityMethod = new StringBuilder();
        // Récupérer le nom de la classe
        String className = clazz.getSimpleName();
        String domainClassName = className.replace("Entity", "");

        // Commencer à construire la méthode toEntity
        toEntityMethod.append("public static ").append(className).append(" toEntity(")
                .append(domainClassName).append(" ").append(domainClassName.toLowerCase()).append(") {\n")
                .append("    return new ").append(className).append("Builder()\n");

        for (Field field : clazz.getDeclaredFields()) {
            if (!Modifier.isStatic(field.getModifiers())) {
                String fieldName = field.getName();
                String capitalizedFieldName = capitalize(fieldName);

                if (!packageClasse.contains(field.getType().getName())) {
                    // Champ non-entité, pas de vérification de nullité nécessaire
                    toEntityMethod.append("            .").append(fieldName)
                            .append("(").append(domainClassName.toLowerCase())
                            .append(".get").append(capitalizedFieldName).append("())\n");
                } else {
                    // Champ de type entité, ajouter une vérification de nullité
                    toEntityMethod.append("            .").append(fieldName).append("(")
                            .append(domainClassName.toLowerCase()).append(".get").append(capitalizedFieldName)
                            .append("() != null ? ").append(field.getType().getSimpleName())
                            .append(".toEntity(").append(domainClassName.toLowerCase())
                            .append(".get").append(capitalizedFieldName).append("()) : null)\n");
                }
            }
        }
        // Terminer les méthodes
        toEntityMethod.append("            .build();\n}\n");

        return toEntityMethod.toString();
    }


    /**
     * Recuperer les noms des classes
     *
     * @param packageName
     * @return
     */
    public List<String> getClassNames(String packageName) {
        List<String> classes = new ArrayList<>();
        String path = "";
        if (fileManager.isWindowsOs()) {
            path = packageName.replace(".", "/");
        } else {
            path = packageName.replace(".", "\\");
        }

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {

            URL resource = classLoader.getResource(path);  // Récupérer la ressource correspondant au chemin du package

            if (resource != null) {
                File directory = new File(resource.toURI());  // Créer un objet File pour accéder au répertoire
                for (File file : Objects.requireNonNull(directory.listFiles())) {
                    String className = packageName + "." + file.getName().replace(".class", "");
                    if (!className.contains("Builder")) {
                        classes.add(className);
                    }

                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return classes;
    }

}
