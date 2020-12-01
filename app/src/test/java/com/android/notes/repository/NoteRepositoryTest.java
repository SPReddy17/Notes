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

   /*
   * insert note
   * verify the correct method is called
   * confirm the observer is triggered
   * confirm new rows are inserted
   * */

    @Test
    void insertNote_returnRow() throws Exception {

        //Arrange

        //Act

        //Assert
    }

    /*insert note
   * Failure(return -1)
   * */

    @Test
    void insertNote_returnFailure() throws Exception {

        //Arrange

        //Act

        //Assert
    }
    /*
   * insert note
   * null title
   * confirm the exception
   * */

    @Test
    void insertNote_nullTitle_throwException() throws Exception {

        //Arrange

        //Act

        //Assert
    }
}

































