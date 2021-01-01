package by.andrew.bot.telegrambotmonitor.botapi;

import by.andrew.bot.telegrambotmonitor.dto.Currency;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

/**
 *
 * ОТКЛЮЧЕН ПОКА (25.12.2020)
 *
 * */

@Component
public class CurrencyAPI {
    private final String URL_CURRENCY_RATE = "https://www.nbrb.by/api/exrates/rates/USD?parammode=2";
    ObjectMapper mapper = new ObjectMapper();

    public CurrencyAPI() {}

    public Currency getCurrencyRate(){
        Currency currency = null;
        try {
            URL url = new URL(URL_CURRENCY_RATE);
            currency = mapper.readValue(url, Currency.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return currency;
    }
}
