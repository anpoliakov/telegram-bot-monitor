package by.andrew.bot.telegrambotmonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class TelegramBotMonitorApplication {
    public static void main(String[] args) {
//        ApiContextInitializer.init();
        SpringApplication.run(TelegramBotMonitorApplication.class, args);
    }


}
