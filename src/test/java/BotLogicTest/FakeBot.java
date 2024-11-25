package BotLogicTest;

import example.bot.Bot;

import java.util.ArrayList;
import java.util.List;

/**
 * Фейковый бот для сохранения всех возвращаемых сообщений
 */
public class FakeBot implements Bot {

    /**
     * Список сообщений
     */
    private final List<String> messages = new ArrayList<>();

    @Override
    public void sendMessage(Long chatId, String message) {
        messages.add(message);
    }

    /**
     * Получение списка сообщений
     * @return список сообщений
     */
    public List<String> getMessages() {
        return messages;
    }
}
