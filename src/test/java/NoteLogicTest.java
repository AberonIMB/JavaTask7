import example.note.NoteLogic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Тесты для логики работы с заметками
 */
public class NoteLogicTest {
    private NoteLogic noteLogic;

    /**
     * создает новый объект перед выполнением каждого теста
     */
    @BeforeEach
    public void setUp() {
        noteLogic = new NoteLogic();
    }

    /**
     * Проверяет корректность добавления заметок и получения списка заметок
     */
    @Test
    public void testAddAndNotes() {
        String addMessage = noteLogic.handleMessage("/add firstNote");
        String notesMessage = noteLogic.handleMessage("/notes");

        Assertions.assertEquals("Note added!", addMessage);
        Assertions.assertEquals("Your notes:\n firstNote", notesMessage);
    }

    /**
     * Проверяет корректность редактирования заметки
     */
    @Test
    public void testEdit() {
        noteLogic.handleMessage("/add firstNote");
        String editMessage = noteLogic.handleMessage("/edit firstNote secondNote");
        String notesMessage = noteLogic.handleMessage("/notes");

        Assertions.assertEquals("Note edited! firstNote -> secondNote", editMessage);
        Assertions.assertEquals("Your notes:\n secondNote", notesMessage);
    }

    /**
     * Проверяет корректность удаления заметки
     */
    @Test
    public void testDel() {
        noteLogic.handleMessage("/add firstNote");
        noteLogic.handleMessage("/add secondNote");
        String delMessage = noteLogic.handleMessage("/del firstNote");
        String notesMessage = noteLogic.handleMessage("/notes");

        Assertions.assertEquals("Note deleted! firstNote", delMessage);
        Assertions.assertEquals("Your notes:\n secondNote", notesMessage);
    }
}