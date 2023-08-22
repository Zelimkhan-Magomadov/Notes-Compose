package zelimkhan.magomadov.notes.ui.notes

import zelimkhan.magomadov.notes.data.NoteType

sealed interface NoteCategoryState {
    data object Notes : NoteCategoryState
    data object Archive : NoteCategoryState
    data object Trash : NoteCategoryState
}

fun NoteCategoryState.asNoteType(): NoteType {
    return when (this) {
        NoteCategoryState.Notes -> NoteType.Note
        NoteCategoryState.Archive -> NoteType.Archived
        NoteCategoryState.Trash -> NoteType.Deleted
    }
}