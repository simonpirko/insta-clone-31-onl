package by.tms.instaclone31onl.core.settings;

import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

public class AppSettings {
    private final String appDbFileStoreFormat;

    private static AppSettings instance;

    private AppSettings(String appDbFileStoreFormat) {
        this.appDbFileStoreFormat = appDbFileStoreFormat;
        instance = this;
    }

    public String appDbFileStoreFormat() {
        return appDbFileStoreFormat;
    }

    public static AppSettings getInstance(){
        return instance;
    }
    public static void configuration(Properties props) throws IOException {
        if(Optional.ofNullable(instance).isPresent())
            return;

        String appDbFileStoreFormat = props.getProperty("app_csv_store_path");

        instance =  new AppSettings(appDbFileStoreFormat);
    }
}
