package com.notes.notesapp.note;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.ArrayList;
import java.util.List;

@WebMvcTest(NoteController.class)
public class NoteControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteService noteService;

    @Test
    public void testGetNotes() throws Exception {
        List<Note> notes = new ArrayList<>();

        notes.add(new Note("UUID1", "Title 1", "Content 1"));
        notes.add(new Note("UUID2", "Title 2", "Content 2"));

        when(noteService.getNotes()).thenReturn(notes);

        mockMvc.perform(get("/api/v1/note/notes"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(notes)));
    }

    @Test
    public void testSaveNote() throws Exception {
        Note note = new Note("UUID","Title","Content");

        mockMvc.perform(post("/api/v1/note")
                .content(asJsonString(note))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(noteService).saveNote(any(Note.class));
    }

    @Test
    public void testDeleteNote() throws Exception {
        String id = "UUID";

        mockMvc.perform(delete("/api/v1/note/{id}", id))
                .andExpect(status().isOk());

        verify(noteService).deleteNote(eq(id));
    }

    @Test
    public void testUpdateNote() throws Exception {
        Note note = new Note("UUID", "Title", "Content");

        mockMvc.perform(put("/api/v1/note/{id}", note.getId())
                .content(asJsonString(note))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(noteService).updateNote(eq(note.getId()), eq(note.getTitle()), eq(note.getContent()));
    }

    // Utility method to convert an object to JSON
    private String asJsonString(Object object) throws Exception {
        return new ObjectMapper().writeValueAsString(object);
    }
}
