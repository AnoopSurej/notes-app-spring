package com.notes.notesapp.note;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class NoteConfig {
    @Bean
    CommandLineRunner commandLineRunner(NoteRepository repository) {
        return args -> {
            Note note1 = new Note(
                    "UUID1",
                    "Title 1",
                    "Content 1"
            );

            Note note2 = new Note(
                    "UUID2",
                    "Title 2",
                    "Content 2"
            );

            repository.saveAll(List.of(note1, note2));
        };
    }
}
