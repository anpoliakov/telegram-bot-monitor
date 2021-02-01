package by.andrew.bot.telegrambotmonitor.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 *
 *
 * */

@JsonIgnoreProperties(ignoreUnknown = true) //Игнорируем те поля, которые не нужны для создания обьекта
public class Currency {
    @JsonProperty("Cur_ID") //Указываем какое поле из JSON должны соотносить с этим полем
    private Integer cur_ID;
    @JsonProperty("Date")
    private String date;
    @JsonProperty("Cur_Abbreviation")
    private String cur_Abbreviation;
    @JsonProperty("Cur_Name")
    private String cur_Name;
    @JsonProperty("Cur_OfficialRate")
    private Double cur_OfficialRate;

    //По умолчанию создаётся обьект и через SET и GET методы внедряются значения
    public Currency() {}

    public Integer getCur_ID() {
        return cur_ID;
    }

    public void setCur_ID(Integer cur_ID) {
        this.cur_ID = cur_ID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCur_Abbreviation() {
        return cur_Abbreviation;
    }

    public void setCur_Abbreviation(String cur_Abbreviation) {
        this.cur_Abbreviation = cur_Abbreviation;
    }

    public String getCur_Name() {
        return cur_Name;
    }

    public void setCur_Name(String cur_Name) {
        this.cur_Name = cur_Name;
    }

    public Double getCur_OfficialRate() {
        return cur_OfficialRate;
    }

    public void setCur_OfficialRate(Double cur_OfficialRate) {
        this.cur_OfficialRate = cur_OfficialRate;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "cur_ID=" + cur_ID +
                ", date='" + date + '\'' +
                ", cur_Abbreviation='" + cur_Abbreviation + '\'' +
                ", cur_Name='" + cur_Name + '\'' +
                ", cur_OfficialRate=" + cur_OfficialRate +
                '}';
    }
}
