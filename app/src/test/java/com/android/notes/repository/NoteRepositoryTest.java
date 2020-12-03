package com.android.notes.repository;

import com.android.notes.models.Note;
import com.android.notes.persistence.NoteDao;
import com.android.notes.ui.Resource;
import com.android.notes.util.TestUtil;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;

import io.reactivex.Single;
import retrofit2.Response;

import static com.android.notes.repository.NoteRepository.INSERT_FAILURE;
import static com.android.notes.repository.NoteRepository.INSERT_SUCCESS;
import static com.android.notes.repository.NoteRepository.NOTE_TITLE_NULL;
import static com.android.notes.repository.NoteRepository.UPDATE_FAILURE;
import static com.android.notes.repository.NoteRepository.UPDATE_SUCCESS;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class NoteRepositoryTest {

    private static final Note NOTE1 = new Note(TestUtil.TEST_NOTE_1);
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
        final Long insertedRow = 1L;
        final Single<Long> returnedData = Single.just(insertedRow);
        when(noteDao.insertNote(any(Note.class))).thenReturn(returnedData);
        //Act
        final Resource<Integer> returnedValue = noteRepository.insertNote(NOTE1).blockingFirst();
        //Assert
        verify(noteDao).insertNote(any(Note.class));
        verifyNoMoreInteractions(noteDao);

        System.out.println("Returned value : " + returnedValue.data);

        assertEquals(Resource.success(1,INSERT_SUCCESS),returnedValue);

        // or test using RxJava
        /*noteRepository.insertNote(NOTE1)
                .test()
                .await()
                .assertValue(Resource.success(1,INSERT_SUCCESS));
        */


    }

    /*insert note
   * Failure(return -1)
   * */

    @Test
    void insertNote_returnFailure() throws Exception {
        //Arrange
        final Long failedInsert = -1L;
        final Single<Long> returnedData = Single.just(failedInsert);
        when(noteDao.insertNote(any(Note.class))).thenReturn(returnedData);
        //Act
        final Resource<Integer> returnedValue = noteRepository.insertNote(NOTE1).blockingFirst();
        //Assert
        verify(noteDao).insertNote(any(Note.class));
        verifyNoMoreInteractions(noteDao);

        assertEquals(Resource.error(null,INSERT_FAILURE),returnedValue);
    }
    /*
   * insert note
   * null title
   * confirm the exception
   * */

    @Test
    void insertNote_nullTitle_throwException() throws Exception {
       Exception exception =  assertThrows(Exception.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                final Note  note = new Note(TestUtil.TEST_NOTE_1);
                note.setTitle(null);
                noteRepository.insertNote(note);
            }
        });
       assertEquals(NOTE_TITLE_NULL,exception.getMessage());
    }


    /*
    * update note
    * verify correct method is called
    * confirm observer is triggered
    * confirm number of rows updated
    * */

    @Test
    void updateNote_returnNumRowsUpdated() throws Exception {

        //Arrange
        final int updatedRow =1 ;
        when(noteDao.updateNote(any(Note.class))).thenReturn(Single.just(updatedRow));

        //Act
        final Resource<Integer> returnedValue = noteRepository.updateNote(NOTE1).blockingFirst();

        //Assert
        verify(noteDao).updateNote(any(Note.class));
        verifyNoMoreInteractions(noteDao);
        assertEquals(Resource.success(updatedRow,UPDATE_SUCCESS),returnedValue);
    }
    /*update note
    * failure(-1)
    * */

    @Test
    void updateNote_returnFailure() throws Exception {

        //Arrange
        final int failedInsert = -1;
        final Single<Integer> returnedData = Single.just(failedInsert);
        when(noteDao.updateNote(any(Note.class))).thenReturn(returnedData);
        //Act
        final Resource<Integer> returnedValue = noteRepository.updateNote(NOTE1).blockingFirst();

        //Assert
        verify(noteDao).updateNote(any(Note.class));
        verifyNoMoreInteractions(noteDao);
        assertEquals(Resource.error(null,UPDATE_FAILURE),returnedValue);
    }
    /*
    * update note
    * null title
    * throw exception
    * */
    @Test
    void updateNote_nullTitle_throwException() throws Exception {
        Exception exception =  assertThrows(Exception.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                final Note  note = new Note(TestUtil.TEST_NOTE_1);
                note.setTitle(null);
                noteRepository.updateNote(note);
            }
        });
        assertEquals(NOTE_TITLE_NULL,exception.getMessage());
    }
}

































