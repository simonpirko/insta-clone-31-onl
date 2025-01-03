package by.tms.instaclone31onl.services;

import by.tms.instaclone31onl.core.constants.AppConstants;
import by.tms.instaclone31onl.core.interfaces.services.FileService;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class InstaFileService implements FileService {
    public byte[] getFileBytes(String path) {
        Path directoriesPath = Path.of(AppConstants.TOMCAT_ROOT, path);
        byte[] bytes = null;
        if(!Files.exists(directoriesPath)){
            return bytes;
        }
        try (FileInputStream stream = new FileInputStream(directoriesPath.toFile())) {
            bytes = stream.readAllBytes();
            return bytes;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
