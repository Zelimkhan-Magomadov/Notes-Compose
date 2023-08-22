package zelimkhan.magomadov.notes.ui.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import zelimkhan.magomadov.notes.data.NotesRepository

class NotesViewModel : ViewModel() {
    private val _notes = MutableStateFlow<List<NoteItemState>>(emptyList())
    val notes = _notes.asStateFlow()

    private val _noteCategoryState = MutableStateFlow<NoteCategoryState>(NoteCategoryState.Notes)
    val noteCategoryState = _noteCategoryState.asStateFlow()

    private val notesRepository = NotesRepository()

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
        val notes = notesRepository.getNotes(noteType)
        _notes.value = notes.map { it.asNoteItem() }
    }
}