package by.tms.instaclone31onl.csv_updater;

import by.tms.instaclone31onl.core.annotations.Entity;
import by.tms.instaclone31onl.core.context.CsvContext;
import by.tms.instaclone31onl.core.models.csv.CsvTable;
import org.reflections.Reflections;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CsvUpdater {
    private static final String TOMCAT_ROOT = System.getenv("CATALINA_HOME");

    public void configuration() throws IOException {
        Reflections reflections = new Reflections("by.tms.instaclone31onl.core.models.entities");
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Entity.class);
        Map<Class<?>, CsvTable> tables = new HashMap<>();
        for (Class cls : classes) {
            Entity table = (Entity) cls.getAnnotation(Entity.class);

            String directoriesPath = TOMCAT_ROOT + table.directories();

            initDirectories(Path.of(directoriesPath));

            Path path = Path.of(directoriesPath, "%s.csv".formatted(table.name())).toAbsolutePath();
            String[] names = Arrays.stream(cls.getDeclaredFields()).map(Field::getName).toArray(String[]::new);

            //check and create if not exists
            initFile(path);

            //create dbTable
            tables.put(cls, CsvTable.getInstance(table.name(), names, path.toString(), cls));
        }
        CsvContext.configuration(tables);
    }

    private static void initDirectories(Path path) throws IOException {
        if(!Files.exists(path)){
            Files.createDirectories(path);
        }
    }

    private static void initFile(Path path) throws IOException {
        if(!Files.exists(path)){
            Files.createFile(path);
        }
    }

    public static CsvUpdater getInstance() {
        return new CsvUpdater();
    }
}
