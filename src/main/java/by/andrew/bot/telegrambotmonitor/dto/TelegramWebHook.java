package by.andrew.bot.telegrambotmonitor.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TelegramWebHook {
    private boolean ok;
    private boolean result;
    private String description;

    public TelegramWebHook() {}

    public TelegramWebHook(boolean ok, boolean result, String description) {
        this.ok = ok;
        this.result = result;
        this.description = description;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "AnswerSetWebHook{" +
                "ok=" + ok +
                ", result=" + result +
                ", description='" + description + '\'' +
                '}';
    }
}
