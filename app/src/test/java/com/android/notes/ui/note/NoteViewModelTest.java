package com.android.notes.ui.note;

import com.android.notes.models.Note;
import com.android.notes.repository.NoteRepository;
import com.android.notes.ui.Resource;
import com.android.notes.util.InstantExecutorExtension;
import com.android.notes.util.LiveDataTestUtil;
import com.android.notes.util.TestUtil;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.NotExtensible;

import io.reactivex.Flowable;
import io.reactivex.internal.operators.single.SingleToFlowable;
import retrofit2.Response;

import static com.android.notes.repository.NoteRepository.INSERT_SUCCESS;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(InstantExecutorExtension.class)
public class NoteViewModelTest {

    //system under test
    private NoteViewModel noteViewModel;


    @Mock
    private NoteRepository noteRepository;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
        noteViewModel = new NoteViewModel(noteRepository);
    }
    /*
    * can't observe a note that hasn't been set
     */

    @Test
    void observeEmptyNoteWhenNoteSet() throws Exception {

        //Arrange
        LiveDataTestUtil<Note> liveDataTestUtil = new LiveDataTestUtil<>();


        //Act
        Note note = liveDataTestUtil.getValue(noteViewModel.observeNote());
        //Assert
        assertNull(note);
    }
    /*
    * observe a note has been set and onChanged will trigger in activity
    * */

    @Test
    void observeNote_whenSet() throws Exception {
        //Arrange
        Note note = new Note(TestUtil.TEST_NOTE_1);
        LiveDataTestUtil<Note> liveDataTestUtil = new LiveDataTestUtil<>();


        //Act
        noteViewModel.setNote(note);
        Note observedNote = liveDataTestUtil.getValue(noteViewModel.observeNote());
        //Assert
        assertEquals(note,observedNote);
    }

    /*
    * Insert a new note and observe row returned
    * */

    @Test
    void insertNote_returnRow() throws Exception {

        //Arrange
        Note note = new Note(TestUtil.TEST_NOTE_1);
        LiveDataTestUtil<Resource<Integer>> liveDataTestUtil = new LiveDataTestUtil<>();
        final int insertedrow = 1;
        Flowable<Resource<Integer>> returnedData = SingleToFlowable.just(Resource.success(insertedrow,INSERT_SUCCESS));
        Mockito.when(noteRepository.insertNote(any(Note.class))).thenReturn(returnedData);

        //Act
        noteViewModel.setNote(note);
        Resource<Integer> returnedValue = liveDataTestUtil.getValue(noteViewModel.insertNote());

        //Assert
        assertEquals(Resource.success(insertedrow,INSERT_SUCCESS),returnedValue);
    }

    /*
    * insert: dont return a new row without observer
    * */

    @Test
    void dontReturnInsertRowWithoutObserver() throws Exception {

        //Arrange
        Note note = new Note(TestUtil.TEST_NOTE_1);

        //Act
        noteViewModel.setNote(note);
        //Assert
        verify(noteRepository,never()).insertNote(any(Note.class));
    }
    /*
    * set note, null title,throw exception
    */

    @Test
    void setNote_nullTitle_throwException() throws Exception {

        //Arrange
         final Note note = new Note (TestUtil.TEST_NOTE_1);
         note.setTitle(null);
         //ASSERT
         assertThrows(Exception.class, new Executable() {
             @Override
             public void execute() throws Throwable {
                 //ACT
                 noteViewModel.setNote(note);
             }
         });
    }
}
