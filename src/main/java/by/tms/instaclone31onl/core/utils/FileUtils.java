package by.tms.instaclone31onl.core.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class FileUtils {
    public static void createDirectoryIfNotExists(Path path) throws IOException {
        if(!Files.exists(path)){
            Files.createDirectories(path);
        }
    }
    public static void createFileIfNotExists(Path path) throws IOException {
        if(!Files.exists(path)){
            Files.createFile(path);
        }
    }

    public static String getExtension(String filename) {
        if (filename == null) {
            return null;
        }
        int dotIndex = filename.lastIndexOf(".");
        if (dotIndex >= 0) {
            return filename.substring(dotIndex + 1);
        }
        return null;
    }
}
