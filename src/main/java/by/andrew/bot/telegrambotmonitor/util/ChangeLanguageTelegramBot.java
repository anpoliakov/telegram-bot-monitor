package by.andrew.bot.telegrambotmonitor.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class ChangeLanguageTelegramBot {
    //TODO: ПРОБЛЕМА в пути к фалу с properties!
    private static final String MAIN_PROPERTIES = "src/main/resources/application.properties";
    private static final String FIELD_WITH_LANGUAGE = "localeTag";

    public static void changeLanguageOn(String language){
        Path path = Paths.get(MAIN_PROPERTIES);

        try(FileInputStream in = new FileInputStream(String.valueOf(path));
            FileOutputStream out = new FileOutputStream(String.valueOf(path))){

            Properties properties = new Properties();

            properties.load(in);                                        //загрузил файл
            properties.setProperty(FIELD_WITH_LANGUAGE, language);      //произвёл измененя
            properties.store(out, null);                       //выгрузил файл

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
