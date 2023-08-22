package com.notes.notesapp.note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "api/v1/note")
public class NoteController {

    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping(path = "notes")
    public List<Note> getNotes() { return noteService.getNotes(); }

    @PostMapping
    public void saveNote(@RequestBody Note note) {
         noteService.saveNote(note);
    }

    @DeleteMapping(path = "{noteId}")
    public void deleteNote(@PathVariable("noteId") String id) { noteService.deleteNote(id); }

    @PutMapping(path = "{noteId}")
    public void updateNote(@PathVariable("noteId") String id,
                           @RequestBody Note note) {
        noteService.updateNote(id, note.getTitle(), note.getContent());
    }
}
