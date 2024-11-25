import example.container.Container;
import example.container.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Тесты для контейнера
 */
public class ContainerTest {
    private Container container;

    /**
     * Создает новый контейнер
     */
    @BeforeEach
    public void setUp() {
        container = new Container();
    }

    /**
     * Проверяет корректность добавления элемента
     */
    @Test
    public void testAddItem() {
        Item item1 = new Item(1);
        boolean answer = container.add(item1);

        Assertions.assertTrue(answer);
        Assertions.assertTrue(container.contains(item1));
        Assertions.assertEquals(1, container.size());
        Assertions.assertEquals(item1, container.get(0));
    }

    /**
     * Проверка корректности удаления элемента
     */
    @Test
    public void testRemoveItem() {
        Item item1 = new Item(1);
        Item item2 = new Item(2);
        container.add(item1);
        container.add(item2);
        boolean answer = container.remove(item1);

        Assertions.assertTrue(answer);
        Assertions.assertTrue(container.contains(item2));
        Assertions.assertEquals(1, container.size());
        Assertions.assertEquals(item2, container.get(0));
    }

    /**
     * Проверка корректности удаления несуществующего элемента
     */
    @Test
    public void testRemoveNotExistItem() {
        Assertions.assertFalse(container.remove(new Item(1)));
    }
}