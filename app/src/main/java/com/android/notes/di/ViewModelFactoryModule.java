package com.android.notes.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.android.notes.ui.note.NoteViewModel;
import com.android.notes.ui.noteslist.NotesListViewModel;
import com.android.notes.viewmodels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelFactoryModule {
    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory viewModelProviderFactory);

    @Binds
    @IntoMap
    @ViewModelKey(NoteViewModel.class)
    public abstract ViewModel bindNoteViewModel(NoteViewModel noteViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(NotesListViewModel.class)
    public abstract ViewModel bindNotesListViewModel(NotesListViewModel noteViewModel);
}
