package org.tickup.adapters.utils;

//import com.itcentrex.adapters.exceptions.FileUploadException;
//import org.primefaces.model.DefaultStreamedContent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Scanner;

public class FileManager {
    private static String pathDirectory = "C:";

    public static String readAppFile(String filePath) {
        try {
            // Le fichier d'entrée
            Resource resource = new ClassPathResource(filePath);
            File file = resource.getFile();
            Scanner scanner = new Scanner(file);
            StringBuilder stringBuilder = new StringBuilder();

            // Renvoie true s'il y a une autre ligne à lire
            while (scanner.hasNextLine()) {

                stringBuilder.append(scanner.nextLine());

            }
            scanner.close();

            return stringBuilder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /*
     * Créer un répertoire
     */
    public static void createDirectory(String pathDirectory) throws IOException {
        Path path = Paths.get(pathDirectory.toLowerCase());
        boolean dirExists = Files.exists(path);
        if (!dirExists) {
            File directory = new File(path.toUri());
            directory.mkdirs();
        }
    }

    /**
     * Créer un répertoire et ses parents s'il n'existe pas
     *
     * @param pathDirectory
     * @return
     */
    public static boolean createDir(String pathDirectory) {
        System.out.println("pathDirectory: " + pathDirectory);
        Path path = Paths.get(pathDirectory);
        System.out.println("path: " + path.toString());
        File directory = new File(path.toUri());
        System.out.println("directory toUri : " + path.toUri());
        return directory.mkdirs();
    }

    /**
     * Permet de vérifier que un répertoire existe
     *
     * @param pathDirectory
     * @return
     */
    public static boolean dirExists(String pathDirectory) {
        boolean fExists = false;
        File directory = new File(pathDirectory);
        if (directory.exists() && directory.isDirectory()) {
            fExists = true;
        }
        return fExists;
    }

    /**
     * Permet de Vérifier que un fichier existe
     *
     * @param pathDirectory
     * @return
     * @throws Exception
     */
    public static boolean fileExists(String pathDirectory) throws Exception {
        boolean fExists = false;
        File directory = new File(pathDirectory);
        if (directory.exists() && directory.isFile()) {
            fExists = true;
        }
        return fExists;
    }

    /**
     * Créer un répertoire
     *
     * @param pathDirectory
     * @return
     * @throws Exception
     */
    public static boolean createDirectories(String pathDirectory) throws Exception {
        File directory = new File(pathDirectory);

        return directory.mkdirs();
    }

    public static void renameDirectory(String pathDirectory, String newPathDirection) throws IOException {
        Path path = Paths.get(pathDirectory.toLowerCase());
        if (Files.exists(path)) {
            File directory = new File(pathDirectory);
            File newDirectory = new File(newPathDirection);
            directory.renameTo(newDirectory);
        }
    }

    public static String getPathDirectory() {
        return FileManager.pathDirectory + File.separatorChar;
    }

    /**
     * Permet de lire le inputStream d'un fichier
     *
     * @param filePath
     * @return
     * @throws FileNotFoundException
     */
    public static InputStream getInputStream(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        return new FileInputStream(file);
    }

    /**
     * Permet de télécharger le stream content d'un fichier
     *
     * @param cheminFichier
     * @param nomFichier
     * @return
     * @throws ParseException
     * @throws Exception
     */
//    public static DefaultStreamedContent telechargerFichier(String cheminFichier, String nomFichier)
//            throws ParseException, Exception {
//
//        if (!fileExists(cheminFichier)) {
//            throw new FileNotFoundException(String.format("%s n'est pas un fichier valide.", nomFichier));
//        }
//        // File file = new File(cheminFichier);
//        DefaultStreamedContent streamedContent = new DefaultStreamedContent();
//        InputStream reportStream = getInputStream(cheminFichier);
//
//        // file.delete();
//        streamedContent = DefaultStreamedContent.builder().name(nomFichier).contentType("application/text")
//                .stream(() -> reportStream).build();
//
//        return streamedContent;
//    }

    /**
     * Permet de téléverser un fichier sur le server de fichier
     *
     * @param src
     * @param filePath
     * @throws FileNotFoundException
     */
//    public static boolean uploadTo(File src, String filePath) throws FileNotFoundException, FileUploadException {
//        return FileManager.fileStorer(new FileInputStream(src), filePath);
//    }

    /**
     * Permet de téléverser le inputStream d'un fichier sur le server de fichier
     *
     * @param src
     * @param filePath
     */
//    public static boolean uploadTo(InputStream src, String filePath) throws FileUploadException {
//        return FileManager.fileStorer(src, filePath);
//    }

    /*
     * Enregistre un fichier
     *
     * private static boolean createFile(InputStream src, String filePath) { File
     * dest = new File(filePath); if (dest.exists()) { dest.delete(); }
     *
     * new File(dest.getParent()).mkdirs(); InputStream is = null; OutputStream os =
     * null;
     *
     * try { is = src; os = new FileOutputStream(dest); byte[] buffer = new
     * byte[1024]; int len; while ((len = is.read(buffer)) > 0) { os.write(buffer,
     * 0, len); } is.close(); os.close(); return true; } catch (IOException e) {
     * e.printStackTrace(); return false; } }
     */

    /**
     * @param fileToStore
     * @param inputStream
     * @return
     */
//    public static boolean fileStorer(InputStream inputStream, String fileToStore) throws FileUploadException {
//        boolean created = false;
//        File createdFile = new File(fileToStore);
//        System.out.println(createdFile.getAbsolutePath());
//        try {
//            createdFile.createNewFile();
//            Files.copy(inputStream, createdFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
//            created = true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new FileUploadException("La création du fichier n'a pas abouti.");
//        }
//        return created;
//    }

    /**
     * Suppression de fichier
     *
     * @param filePath
     */
    public static void delete(String filePath) {
        File file = new File(filePath);
        file.delete();
    }

    public static boolean deleteFile(String urlArchivage) throws Exception {
        boolean fDeleted = false;
        if (fileExists(urlArchivage)) {
            delete(urlArchivage);
            fDeleted = true;
        }
        return fDeleted;
    }


    public static String buildPath(String... parts) {
        return String.join(File.separator, parts);
    }

    public static String getFileExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf('.');
        if (lastIndex > 0 && lastIndex < fileName.length() - 1) {
            return fileName.substring(lastIndex + 1);
        } else {
            return ""; // Pas d'extension trouvée
        }
    }

    public boolean isWindowsOs() {
        return System.getProperty("os.name").contains("Win");
    }

    private String getBaseDir() {
        return this.isWindowsOs() ? buildPath(Constant.CHEMIN_BASE_DIR_WIN.split(",")) : buildPath(Constant.CHEMIN_BASE_DIR.split(","));
    }

    private File getFile(String filePath) {
        String path = buildPath(getBaseDir(), filePath);
        path = !this.isWindowsOs() ? path.replaceAll("\\\\", File.separator) : path.replaceAll("/", File.separator);
        return new File(path);
    }


}
