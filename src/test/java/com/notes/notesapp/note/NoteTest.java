package com.notes.notesapp.note;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NoteTest {
    @Test
    public void testConstructorsAndGetters() {
        String id = "UUID";
        String title = "Test Title";
        String content = "Test Content";

        Note note = new Note(id, title, content);

        assertEquals(id, note.getId());
        assertEquals(title, note.getTitle());
        assertEquals(content, note.getContent());
    }

    @Test
    public void testSetters() {
        Note note = new Note();

        note.setId("New-UUID");
        note.setTitle("New Title");
        note.setContent("New Content");

        assertEquals("New-UUID", note.getId());
        assertEquals("New Title", note.getTitle());
        assertEquals("New Content", note.getContent());
    }

    @Test
    public void testToString() {
        // Arrange
        String id = "789";
        String title = "ToString Title";
        String content = "ToString Content";
        Note note = new Note(id, title, content);

        // Act
        String toStringResult = note.toString();

        // Assert
        String expectedToString = "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
        assertEquals(expectedToString, toStringResult);
    }
}
