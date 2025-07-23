package org.etix.domain.utils;

import java.io.File;
import java.io.InputStream;

public interface FileManager {
    /**
     * Ecrire un ficher a partir de son input stream
     *
     * @param filePath
     * @param fileStream
     * @return
     */
    public File store(String filePath, InputStream fileStream);

    /**
     * Ecrire un ficher a partir de son chemin d origine
     *
     * @param filePath
     * @param localPath
     * @return
     */
    public boolean store(String filePath, String localPath);

    /**
     * Ecrire un ficher a partir d un ficher existant
     *
     * @param filePath
     * @param localFile
     * @return
     */
    public boolean store(String filePath, File localFile);

    /**
     * Creer un repertoire s il n existe pas
     *
     * @param dirPath
     * @return
     */
    public boolean mkdir(String dirPath);

    /**
     * Creer un repertoire et ses predecesseur s ils n existent pas
     *
     * @param dirPath
     * @return
     */
    public boolean mkdirs(String dirPath);

    /**
     * Verifier si un repertoire existe
     *
     * @param dirPath
     * @return
     */
    public boolean dirExists(String dirPath);

    /**
     * Verifier si un ficher existe
     *
     * @param filePath
     * @return
     */
    public boolean fileExists(String filePath);

    /**
     * Recuperer un fichier s'il existe
     *
     * @param filePath
     * @return
     */
    public File getFile(String filePath);

    /*  public String getFileHttpAdresse(String filePath);*/

    public boolean isWindowsOs();

    String getBaseDir();

    public String getFileUrl(String documentUrl);

    String buildPath(String... parts);

    String buildHttpPath(String... parts);

    String getFileExtention(String fileName);

    boolean deleteFile(String filePath);
}
