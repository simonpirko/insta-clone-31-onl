package by.tms.instaclone31onl.configuration;

import by.tms.instaclone31onl.core.settings.AppSettings;
import jakarta.servlet.ServletContextEvent;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class AppConfiguration {

    public void configure(ServletContextEvent context) throws IOException {
        //set main settings
        AppSettings.configuration(getProperties("app.properties"));
    }

    private Properties getProperties(String path){
        Properties props = new Properties();
        try (InputStream in = getClass().getClassLoader().getResourceAsStream(path)){
            props.load(in);
        }
        catch (IOException e) {
            return null;
        }
        return props;
    }

    public static AppConfiguration getConfiguration() {
        return new AppConfiguration();
    }
}