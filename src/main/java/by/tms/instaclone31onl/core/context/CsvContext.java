package by.tms.instaclone31onl.core.context;

import by.tms.instaclone31onl.core.models.csv.CsvTable;

import java.util.Map;

public class CsvContext {
    private static CsvContext instance;
    private final Map<Class<?>, CsvTable> maps;
    private CsvContext(Map<Class<?>, CsvTable> maps) {
        this.maps = maps;
    }

    public CsvTable getCsvTable(final Class<?> clazz) {
        return maps.getOrDefault(clazz, null);
    }

    public static void configuration(Map<Class<?>, CsvTable> maps){
        if(instance != null){
            return;
        }
        instance = new CsvContext(maps);
    }
    public static CsvContext getInstance()
    {
        return instance;
    }
}
