package org.etix.adapters.utils;

import org.primefaces.model.file.UploadedFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UploadedFileUtil {

    public static final String XLS = "XLS";
    public static final String XLSX = "XLSX";

    /**
     * @param file
     * @return
     */
    public static boolean isValidUploadFile(UploadedFile file) {
        return file != null && file.getContent() != null && file.getContent().length > 0 && file.getFileName() != null;
    }


    /**
     * @param filename
     * @return
     */
    public static String unifyFileName(String filename) {
        String fileExtension = filename.substring(filename.lastIndexOf(".") + 1);
        return filename.substring(0, filename.lastIndexOf(".")) + randomString() + "." + fileExtension;
    }


    /**
     * yyyyMMddhhmm
     *
     * @return
     */
    private static String randomString() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddmmSSS"));
    }


    /**
     * @param name
     * @return
     */
    public static String trim(String name) {
        return name.replaceAll(" ", "");
    }

    /**
     * @param name
     * @return
     */
    public static String parse(String name) {
        String[] parts = name.split("@");
        String folder = parts[parts.length - 1];
        return folder;
    }

    public static boolean isAcceptedExtensions(String fileExtension) {
        return fileExtension.equalsIgnoreCase(XLS) || fileExtension.equalsIgnoreCase(XLSX);
    }


}
