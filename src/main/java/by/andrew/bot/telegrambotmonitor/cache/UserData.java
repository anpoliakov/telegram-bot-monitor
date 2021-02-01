package by.andrew.bot.telegrambotmonitor.cache;

/**
 *
 * Роль класса - хранение пользовательских данных (*пока только - выбранного языка)
 *
 * */

public class UserData {
    private String selectedLanguage;

    public UserData(String selectedLanguage) {
        this.selectedLanguage = selectedLanguage;
    }

    public String getSelectedLanguage() {
        return selectedLanguage;
    }
    public void setSelectedLanguage(String selectedLanguage) {
        this.selectedLanguage = selectedLanguage;
    }
}
