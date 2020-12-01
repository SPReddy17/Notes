package com.android.notes.repository;

import com.android.notes.persistence.NoteDao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class NoteRepositoryTest {

    //system under test
    private NoteRepository noteRepository;

   //@Mock
   private NoteDao noteDao;

   @BeforeEach
    public void initEach(){
       // MockitoAnnotations.initMocks(this);
       noteDao = mock(NoteDao.class);

       noteRepository = new NoteRepository(noteDao);

   }

    @Test
    void dummTest() throws Exception {

        assertNotNull(noteDao);
        assertNotNull(noteRepository);

    }
}

































