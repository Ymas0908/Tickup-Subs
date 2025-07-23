package org.etix.domain.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Génère et écrit des classes à partir des classes spécifiées dans le package donné.
 *
 * @param packageName Le nom du package contenant les classes à traiter.
 * @throws ClassNotFoundException Si une classe spécifiée ne peut pas être trouvée.
 * @throws IOException Si une erreur se produit lors de l'écriture des fichiers.
 */

public class ClassGenerator {

    public static List<String> generateClasses(List<Class<?>> classes, String packageName) {
        List<String> generatedClasses = new ArrayList<>(); // Liste pour stocker les classes générées

        for (Class<?> clazz : classes) { // Parcourir chaque classe fournie
            String className = clazz.getSimpleName(); // Obtenir le nom simple de la classe (sans package)
            String newClassName = className.replace("Entity", "Response"); // Modifier le nom de la classe

            StringBuilder classBuilder = new StringBuilder(); // Utiliser StringBuilder pour construire le code de la nouvelle classe
            classBuilder.append("package ").append(packageName).append(";\n\n")
                    .append("import com.fasterxml.jackson.annotation.JsonProperty;\n")
                    .append("import lombok.AllArgsConstructor;\n")
                    .append("import lombok.Getter;\n")
                    .append("@AllArgsConstructor\n")
                    .append("@Getter\n")
                    .append("// Classe générée automatiquement\n")
                    .append("public class ").append(newClassName).append(" {\n\n");

            Field[] fields = clazz.getDeclaredFields(); // Récupérer tous les champs de la classe d'origine
            for (Field field : fields) { // Parcourir chaque champ
                String fieldType = field.getType().getSimpleName(); // Obtenir le type du champ
                String fieldName = field.getName(); // Obtenir le nom du champ
                classBuilder.append("    @JsonProperty\n");
                classBuilder.append("    private final ").append(fieldType).append(" ").append(fieldName).append(";\n");
            }

            // Méthode statique of pour créer une instance de ClasseResponse à partir d'un objet Class
            String name = clazz.getSimpleName().replace("Entity", "");  //Modifier le nom de la classe
            classBuilder.append("\n    public static ").append(newClassName).append(" of(").append(name).append(" model) {\n");
            classBuilder.append("        return new ").append(newClassName).append("(");

            for (int i = 0; i < fields.length; i++) {  // Itérer sur les champs
                Field field = fields[i];
                String fieldName = field.getName();
                classBuilder.append("\n                          model.").append(fieldName).append("()");  // Appeler les méthodes correspondantes sur entity

                if (i < fields.length - 1) {
                    classBuilder.append(", ");  // Ajouter une virgule si ce n'est pas le dernier champ
                }
            }

            classBuilder.append(");\n    }\n");  // Terminer l'appel du constructeur et la méthode
            classBuilder.append("}");  // Terminer l'appel de la classe

            generatedClasses.add(classBuilder.toString()); // Ajouter la classe générée à la liste des classes générées
        }

        return generatedClasses; // Retourner la liste des classes générées
    }

    public static void constructToClass(String packageName) throws ClassNotFoundException, IOException {
        List<Class<?>> classes = getClasses(packageName);  // Récupérer les classes à partir du package spécifié

        List<String> generatedClasses = generateClasses(classes, packageName);  // Générer les nouvelles classes


/*        String packagePath = "src/main/java/";
        URL url = Thread.currentThread().getContextClassLoader().getResource(packagePath.replace('.', '/'));
        if (url != null) {
            String path = url.getPath();
            File directory = new File(path);
            System.out.println("Répertoire src/main/java/ : " + directory.getAbsolutePath());
        } else {
            System.out.println("Impossible de trouver le répertoire src/main/java/");
        }*/


        String directoryPath = "src/main/java/" + packageName.replace(".", File.separator);
        File directory = new File(directoryPath);
        // Vérifier si le répertoire existe, sinon le créer
        if (!directory.exists()) {
            directory.mkdirs(); // Créer tous les répertoires nécessaires
        }

        for (String generatedClass : generatedClasses) {
            Pattern pattern = Pattern.compile("class\\s+(\\w+)");
            Matcher matcher = pattern.matcher(generatedClass);

            String className = null;
            if (matcher.find()) {
                className = matcher.group(1);  // Récupérer le nom de la classe à partir du code généré
            }

            if (className != null) {
                File file = new File(directory, className + ".java");  // Créer un fichier pour la nouvelle classe

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write(generatedClass);  // Écrire le code de la classe dans le fichier
                    System.out.println("Classe générée : " + file.getAbsolutePath());  // Afficher l'emplacement du fichier créé
                } catch (IOException e) {
                    System.err.println("Erreur lors de l'écriture du fichier : " + e.getMessage());  // Gérer l'erreur d'écriture
                    throw e;
                }
            } else {
                System.err.println("Nom de classe non trouvé dans le code généré.");  // Gérer l'absence de nom de classe
            }
        }
    }

    public static List<Class<?>> getClasses(String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();  // Liste pour stocker les classes trouvées
        String path = packageName.replace(".", "/");  // Convertir le nom du package en chemin

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();  // Obtenir le ClassLoader courant

        try {
            URL resource = classLoader.getResource(path);  // Récupérer la ressource correspondant au chemin du package

            if (resource != null) {
                File directory = new File(resource.toURI());  // Créer un objet File pour accéder au répertoire

                for (File file : Objects.requireNonNull(directory.listFiles())) {
                    if (file.getName().endsWith(".class")) {  // Vérifier si c'est un fichier .class
                        String className = packageName + '.' + file.getName().replace(".class", "");
                        classes.add(Class.forName(className));  // Ajouter la classe à la liste en utilisant son nom complet
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();  // Afficher l'erreur si quelque chose échoue
        }

        return classes;  // Retourner la liste des classes trouvées
    }
}