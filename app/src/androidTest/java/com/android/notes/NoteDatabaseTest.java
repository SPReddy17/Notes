package com.android.notes;

import android.app.Application;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.android.notes.persistence.NoteDao;
import com.android.notes.persistence.NoteDatabase;

import org.junit.After;
import org.junit.Before;

public abstract class NoteDatabaseTest {

    //system under test
    private NoteDatabase noteDatabase;

    public NoteDao getNoteDao(){
        return noteDatabase.getNoteDao();
    }

    @Before
    public  void init(){
        noteDatabase = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                NoteDatabase.class
        ).build();
    }

    @After
    public void finish(){
        noteDatabase.close();
    }

}
