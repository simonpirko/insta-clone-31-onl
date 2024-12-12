package by.tms.instaclone31onl.csvupdater;

import by.tms.instaclone31onl.core.annotations.Entity;
import by.tms.instaclone31onl.core.contexts.CsvContext;
import by.tms.instaclone31onl.core.models.csv.CsvTable;
import by.tms.instaclone31onl.core.settings.AppSettings;
import jakarta.servlet.ServletContextEvent;
import org.reflections.Reflections;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.*;


public class CsvUpdater {

    public void configuration(ServletContextEvent sce) throws IOException {
        AppSettings settings = AppSettings.getInstance();
        String mainPath = settings != null && settings.appDbFileStoreFormat() != null
                ? settings.appDbFileStoreFormat()
                : null;

        Reflections reflections = new Reflections("by.tms.instaclone31onl.core.models.entities");
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Entity.class);
        Map<Class<?>, CsvTable> tables = new HashMap<>();
        for (Class cls : classes) {
            Entity table = (Entity) cls.getAnnotation(Entity.class);
            String fileName = "%s.csv".formatted(table.name());
            String path = mainPath != null
                    ? Path.of(mainPath, fileName).toAbsolutePath().toString()
                    : sce.getServletContext().getRealPath(fileName);
            Field[] fields = cls.getDeclaredFields();
            String[] names = Arrays.stream(fields).map(Field::getName).toArray(String[]::new);
            //check and create if not exists
            File dbFile = new File(path);

            dbFile.createNewFile();

            //create dbTable
            tables.put(cls, CsvTable.getInstance(table.name(), names, path, cls));
        }

        CsvContext.configuration(tables);

    }

    public static CsvUpdater getInstance() throws IOException {
        return new CsvUpdater();
    }
}
