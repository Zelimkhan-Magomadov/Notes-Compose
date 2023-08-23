package zelimkhan.magomadov.notes.ui.notes

import zelimkhan.magomadov.notes.data.NoteTypeLocal

sealed interface NoteCategoryState {
    data object Notes : NoteCategoryState
    data object Archive : NoteCategoryState
    data object Trash : NoteCategoryState
}

fun NoteCategoryState.asNoteType(): NoteTypeLocal {
    return when (this) {
        NoteCategoryState.Notes -> NoteTypeLocal.Note
        NoteCategoryState.Archive -> NoteTypeLocal.Archived
        NoteCategoryState.Trash -> NoteTypeLocal.Deleted
    }
}