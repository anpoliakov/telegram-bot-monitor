package by.andrew.bot.telegrambotmonitor.controller;

import by.andrew.bot.telegrambotmonitor.WebHookBot;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
public class WebHookController {
    private final WebHookBot webHookBot;

    public WebHookController(WebHookBot webHookBot) {
        this.webHookBot = webHookBot;
    }

    @PostMapping
    public BotApiMethod onUpdateReceived(@RequestBody Update update){
        System.out.println("onUpdateReceived() бина 'WebHookController'");
        return webHookBot.onWebhookUpdateReceived(update);
    }
}
