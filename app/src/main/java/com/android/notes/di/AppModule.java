package com.android.notes.di;

import android.app.Application;

import androidx.room.PrimaryKey;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.android.notes.models.Note;
import com.android.notes.persistence.NoteDao;
import com.android.notes.persistence.NoteDatabase;
import com.android.notes.repository.NoteRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static com.android.notes.persistence.NoteDatabase.DATABASE_NAME;

@Module
public class AppModule {


    @Singleton
    @Provides
    static NoteDatabase provideNoteDatabase(Application application){
        return Room.databaseBuilder(
                application,
                NoteDatabase.class,
                DATABASE_NAME
        ).build();
    }

    @Singleton
    @Provides
    static NoteDao provideNoteDao(NoteDatabase noteDatabase){
        return noteDatabase.getNoteDao();
    }

    @Singleton
    @Provides
    static NoteRepository provideNoteRepository(NoteDao noteDao){
        return new NoteRepository(noteDao);
    }
}
