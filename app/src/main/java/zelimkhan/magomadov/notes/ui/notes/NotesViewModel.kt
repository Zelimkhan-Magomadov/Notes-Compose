package zelimkhan.magomadov.notes.ui.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import zelimkhan.magomadov.notes.data.note.NoteRepository
import zelimkhan.magomadov.notes.ui.notes.state.NoteCategoryState
import zelimkhan.magomadov.notes.ui.notes.state.NoteItemState
import zelimkhan.magomadov.notes.ui.notes.state.NotesIntent
import zelimkhan.magomadov.notes.ui.notes.state.asNoteItem
import zelimkhan.magomadov.notes.ui.notes.state.asNoteType
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {
    private val _notes = MutableStateFlow<List<NoteItemState>>(emptyList())
    val notes = _notes.asStateFlow()

    private val _noteCategoryState = MutableStateFlow<NoteCategoryState>(NoteCategoryState.Notes)
    val noteCategoryState = _noteCategoryState.asStateFlow()

    init {
        viewModelScope.launch {
            loadNotes()
        }
    }

    fun onIntent(intent: NotesIntent) {
        viewModelScope.launch {
            when (intent) {
                NotesIntent.SelectNotesCategory -> selectCategory(NoteCategoryState.Notes)
                NotesIntent.SelectArchiveCategory -> selectCategory(NoteCategoryState.Archive)
                NotesIntent.SelectTrashCategory -> selectCategory(NoteCategoryState.Trash)
            }
        }
    }

    private fun selectCategory(noteCategoryState: NoteCategoryState) {
        if (_noteCategoryState.value != noteCategoryState) {
            _noteCategoryState.value = noteCategoryState
            loadNotes()
        }
    }

    private fun loadNotes() {
        val noteType = noteCategoryState.value.asNoteType()
        noteRepository.get(noteType)
            .map { notes -> notes.map { it.asNoteItem() } }
            .onEach { transformedNotes -> _notes.value = transformedNotes }
            .launchIn(viewModelScope)
    }
}