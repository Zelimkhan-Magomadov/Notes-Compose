package zelimkhan.magomadov.notes.ui.note

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import zelimkhan.magomadov.notes.data.NotesRepository
import zelimkhan.magomadov.notes.ui.navArgs

class NoteViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _noteState = MutableStateFlow(NoteState())
    val noteState = _noteState.asStateFlow()

    private val notesRepository = NotesRepository()

    init {
        viewModelScope.launch {
            val navArgs: NoteScreenNavArgs = savedStateHandle.navArgs()
            val noteId = navArgs.id
            _noteState.value = if (noteId != 0L)
                notesRepository.getNote(noteId).asNoteState()
            else
                notesRepository.createNote().asNoteState()
        }
    }

    fun onIntent(intent: NoteIntent) {
        viewModelScope.launch {
            when (intent) {
                is NoteIntent.TitleChange -> _noteState.update { it.copy(title = intent.value) }
                is NoteIntent.TextChange -> _noteState.update { it.copy(text = intent.value) }
            }
            println("========================================")
            notesRepository.save(noteState.value.asNoteLocal())
        }
    }
}