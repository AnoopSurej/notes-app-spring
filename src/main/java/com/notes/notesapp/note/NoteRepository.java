package com.notes.notesapp.note;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, String> {
}
