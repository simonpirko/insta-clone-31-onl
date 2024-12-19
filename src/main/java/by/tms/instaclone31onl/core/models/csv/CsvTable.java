package by.tms.instaclone31onl.core.models.csv;

public class CsvTable<T> {
    private final String name;
    private final String[] header;
    private final String path;
    private final Class<T> aClass;

    public CsvTable(final String name, final String[] header, final String path, Class<T> aClass) {
        this.name = name;
        this.header = header;
        this.path = path;
        this.aClass = aClass;
    }
    public String getName() {
        return name;
    }
    public String[] getHeader() {
        return header;
    }
    public String getPath() {
        return path;
    }
    public Class<T> getaClass() {
        return aClass;
    }

    public static <T> CsvTable<T> getInstance(final String name, final String[] header, final String path, Class<T> aClass) {
        return new CsvTable<T>(name, header, path, aClass);
    }

}
