package com.notes.notesapp.note;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> getNotes() {
        return noteRepository.findAll();
    }

    public void saveNote(Note note) {
        noteRepository.save(note);
        System.out.println("Saved note "+note);
    }

    public void deleteNote(String id) {
        if(!noteRepository.existsById(id)) throw new IllegalStateException("Note with id "+id+" doesn't exist");
        noteRepository.deleteById(id);
        System.out.println("Deleted note with id "+id);
    }

    @Transactional
    public void updateNote(String id, String title, String content) {
        System.out.println("Triggered update for note with id "+id);
        Note note = noteRepository.findById(id).orElseThrow(() -> new IllegalStateException("Note with id: "+id+" doesn't exist"));
        if(title!=null && !title.isEmpty() && !Objects.equals(note.getTitle(), title)) {
            note.setTitle(title);
            System.out.println("Updated title!");
        }
        if(content!=null && !content.isEmpty() && !Objects.equals(note.getContent(), content)) {
            note.setContent(content);
            System.out.println("Updated content!");
        }
    }
}
