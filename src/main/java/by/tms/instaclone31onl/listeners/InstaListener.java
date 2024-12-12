package by.tms.instaclone31onl.listeners;

import by.tms.instaclone31onl.configuration.AppConfiguration;
import by.tms.instaclone31onl.csvupdater.CsvUpdater;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class InstaListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            AppConfiguration.getConfiguration().configure(sce);
            CsvUpdater.getInstance().configuration(sce);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        ServletContextListener.super.contextInitialized(sce);
    }
}
