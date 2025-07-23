package org.etix.adapters.utils;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.etix.adapters.exceptions.FileUploadException;
import org.etix.domain.utils.FileManager;
import org.primefaces.shaded.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@Slf4j
@Component
@AllArgsConstructor
public class DiskFileManager implements FileManager {

    // private final ParametreService parametreService;

   /* @Value("${storage.base.dir}")
    private String[] baseDirParts;

    @Value("${storage.base.dir.win}")
    private String[] baseDirPartsWin;*/

    @Override
    public File store(String filePath, InputStream fileStream) {
        boolean created = false;

        File file = new File(filePath);
        try {
            created = file.createNewFile();
            created = Files.copy(fileStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING) > 0;
            if (created) {
                return file;
            } else {
                throw new FileUploadException("L'upload du fichier n'a pas abouti.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error(ex.getMessage());
            throw new FileUploadException("L'upload du fichier n'a pas abouti.");

        }

    }

    @Override
    public boolean store(String filePath, String localPath) {
        boolean created = false;
        // File fileTarget = getFile(filePath);
        File fileTarget = new File(filePath);
        return moveFile(localPath, created, fileTarget);
    }

    private boolean moveFile(String localPath, boolean created, File fileTarget) {
        // File fileSource = getFile(localPath);
        File fileSource = new File(localPath);
        try {
            created = fileTarget.createNewFile();
            Files.copy(fileSource.toPath(), fileTarget.toPath(), StandardCopyOption.REPLACE_EXISTING);
            created = true;

        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new FileUploadException("Le deplacement du repertoire n'a pas abouti.", ex);
        }
        return created;
    }

    @Override
    public boolean store(String filePath, File localFile) {
        boolean created = false;
        return moveFile(filePath, created, localFile);
    }

    @Override
    public boolean mkdir(String dirPath) {
        boolean created = false;
        try {
            //File file = getFile(dirPath);
            File file = new File(dirPath);
            if (file.exists()) {
                created = true;
            } else {
                created = file.mkdir();
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            ex.printStackTrace();
            throw new FileUploadException("La création du repertoire n'a pas abouti.", ex);
        }
        return created;
    }

    @Override
    public boolean mkdirs(String dirPath) {
        boolean created = false;
        try {
            if (dirExists(dirPath)) {
                created = true;
            } else {
                // File file = getFile(dirPath);
                File file = new File(dirPath);
                created = file.mkdirs();
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            ex.printStackTrace();
            throw new FileUploadException("La création du repertoire n'a pas abouti.", ex);
        }
        return created;
    }

    @Override
    public boolean dirExists(String dirPath) {
        boolean exists = false;
        try {
            // File file = getFile(dirPath);
            File file = new File(dirPath);
            if (file.exists() && file.isDirectory()) {
                exists = true;
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            ex.printStackTrace();
            throw new FileUploadException("La vérification du repertoire n'a pas abouti.", ex);
        }
        return exists;
    }

    @Override
    public boolean fileExists(String filePath) {
        boolean exists = false;
        try {
            // File file = getFile(filePath);
            File file = new File(filePath);
            if (file.exists() && file.isFile()) {
                exists = true;
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new FileUploadException("La vérification du fichier n'a pas abouti.", ex);
        }
        return exists;
    }

    @Override
    public File getFile(String filePath) {
        String path = this.buildPath(getBaseDir(), filePath);
        path = !this.isWindowsOs() ? path.replaceAll("\\\\", File.separator) : path.replaceAll("/", File.separator);
        return new File(path);
    }

    /**
     * @return
     */
    @Override
    public String getBaseDir() {

        return this.isWindowsOs() ? this.buildPath(Constant.CHEMIN_BASE_DIR_WIN.split(",")) : this.buildPath(this.buildPath(Constant.CHEMIN_BASE_DIR.split(",")));
    }

   /* @Override
    public String getFileHttpAdresse(String filePath) {
        String path = FileManagerUtil.buildHttpPath(getBaseDirHttp(), filePath);
        return FileManagerUtil.buildHttpPath(path.split("\\\\")).replaceAll("STORAGES/", "");
    }*/

    public String getFileUrl(String documentUrl) {
        String path = this.buildPath(getBaseDir(), documentUrl);
        return this.buildHttpPath(path.split("\\\\"));
    }

   /* public String getBaseDirHttp() {
        return parametreService.getParametres().get(0).getParamServerWeb() + FileManagerUtil.buildPath(parametreService.getParametres().get(0).getParamRepFtp().split(","));
    }*/

    @Override
    public boolean isWindowsOs() {
        return System.getProperty("os.name").contains("Win");
    }

    @Override
    public String buildPath(String... parts) {
        return String.join(File.separator, parts);
    }

    @Override
    public String buildHttpPath(String... parts) {
        return String.join("/", parts);
    }


    @Override
    public String getFileExtention(String fileName) {
        return FilenameUtils.getExtension(fileName);
    }

    @Override
    public boolean deleteFile(String filePath) {
        try {
            File file = new File(filePath);
            if (file.exists() && file.isFile()) {
                return file.delete();
            } else {
                log.warn("Le fichier à supprimer n'existe pas ou n'est pas un fichier : {}", filePath);
                return false;
            }
        } catch (Exception ex) {
            log.error("Erreur lors de la suppression du fichier : {}", ex.getMessage(), ex);
            throw new FileUploadException("La suppression du fichier a échoué.", ex);
        }
    }
}
