package BotLogicTest;

import example.bot.BotLogic;
import example.bot.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**\
 * Тесты для логики работы бота
 */
public class BotLogicTest {
    private final FakeBot fakeBot = new FakeBot();
    private final BotLogic botLogic = new BotLogic(fakeBot);
    private final User user = new User(1L);

    /**
     * Запуск бота
     */
    @BeforeEach
    public void setUp() {
        botLogic.processCommand(user, "/test");

    }

    /**
     * Проверяет, что бот корректно реагирует на правильные ответы
     */
    @Test
    public void testAnswerCorrect() {
        Assertions.assertEquals("Вычислите степень: 10^2", fakeBot.getMessages().getFirst());
        botLogic.processCommand(user, "100");
        Assertions.assertEquals("Правильный ответ!", fakeBot.getMessages().get(1));

        Assertions.assertEquals("Сколько будет 2 + 2 * 2", fakeBot.getMessages().get(2));
        botLogic.processCommand(user, "6");
        Assertions.assertEquals("Правильный ответ!", fakeBot.getMessages().get(3));

        botLogic.processCommand(user, "/repeat");
        Assertions.assertEquals("Нет вопросов для повторения", fakeBot.getMessages().getLast());
        Assertions.assertEquals(0, user.getWrongAnswerQuestions().size());
    }

    /**
     * Проверяет, что бот корректно реагирует на неправильные ответы
     */
    @Test
    public void testAnswerWrong() {
        botLogic.processCommand(user, "101");
        Assertions.assertEquals("Вы ошиблись, верный ответ: 100", fakeBot.getMessages().get(1));
        Assertions.assertEquals(1, user.getWrongAnswerQuestions().size());

        botLogic.processCommand(user, "65");
        Assertions.assertEquals("Вы ошиблись, верный ответ: 6", fakeBot.getMessages().get(3));
        Assertions.assertEquals(2, user.getWrongAnswerQuestions().size());
    }

    /**
     * Проверяет, что бот корректно добавляет вопросы в список для повторения
     * Проверяет,что бот корректно удаляет вопросы из списка для повторения
     */
    @Test
    public void testAddRemoveRepeatQuestions() {
        botLogic.processCommand(user, "101");
        botLogic.processCommand(user, "65");
        botLogic.processCommand(user, "/repeat");

        Assertions.assertEquals("Вычислите степень: 10^2", fakeBot.getMessages().get(5));
        botLogic.processCommand(user, "100");
        Assertions.assertEquals("Правильный ответ!", fakeBot.getMessages().get(6));

        Assertions.assertEquals("Сколько будет 2 + 2 * 2", fakeBot.getMessages().get(7));
        botLogic.processCommand(user, "6");
        Assertions.assertEquals("Правильный ответ!", fakeBot.getMessages().get(8));

        Assertions.assertEquals("Тест завершен", fakeBot.getMessages().get(9));
        Assertions.assertEquals(0, user.getWrongAnswerQuestions().size());
        botLogic.processCommand(user, "/repeat");
        Assertions.assertEquals("Нет вопросов для повторения", fakeBot.getMessages().get(10));
    }

    /**
     * Проверяет, что бот корректно устанавливает и выводит напомнинание
     */
    @Test
    public void testNotification() throws InterruptedException {
        botLogic.processCommand(user, "/notify");
        Assertions.assertEquals("Введите текст напоминания", fakeBot.getMessages().get(1));
        botLogic.processCommand(user, "Напоминание");
        Assertions.assertEquals("Через сколько секунд напомнить?", fakeBot.getMessages().get(2));
        botLogic.processCommand(user, "1");
        Assertions.assertEquals("Напоминание установлено", fakeBot.getMessages().get(3));
        Assertions.assertEquals(4, fakeBot.getMessages().size());
        Thread.sleep(1020);
        Assertions.assertEquals("Сработало напоминание: 'Напоминание'", fakeBot.getMessages().get(4));
    }
}

