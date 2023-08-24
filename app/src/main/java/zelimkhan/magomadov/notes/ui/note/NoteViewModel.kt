package zelimkhan.magomadov.notes.ui.note

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import zelimkhan.magomadov.notes.data.note.NoteRepository
import zelimkhan.magomadov.notes.ui.navArgs
import zelimkhan.magomadov.notes.ui.note.state.NoteIntent
import zelimkhan.magomadov.notes.ui.note.state.NoteScreenNavArgs
import zelimkhan.magomadov.notes.ui.note.state.NoteState
import zelimkhan.magomadov.notes.ui.note.state.asNoteLocal
import zelimkhan.magomadov.notes.ui.note.state.asNoteState
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _noteState = MutableStateFlow(NoteState())
    val noteState = _noteState.asStateFlow()

    init {
        viewModelScope.launch {
            val navArgs: NoteScreenNavArgs = savedStateHandle.navArgs()
            val noteId = navArgs.id
            _noteState.value = if (noteId != 0L)
                noteRepository.get(noteId).asNoteState()
            else
                NoteState(id = noteRepository.add())
        }
    }

    fun onIntent(intent: NoteIntent) {
        viewModelScope.launch {
            when (intent) {
                is NoteIntent.TitleChange -> _noteState.update { it.copy(title = intent.value) }
                is NoteIntent.TextChange -> _noteState.update { it.copy(text = intent.value) }
            }
            noteRepository.update(noteState.value.asNoteLocal())
        }
    }
}