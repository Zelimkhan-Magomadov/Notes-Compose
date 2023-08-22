package zelimkhan.magomadov.notes.ui.notes

sealed interface NotesIntent {
    data object SelectNotesCategory : NotesIntent
    data object SelectArchiveCategory : NotesIntent
    data object SelectTrashCategory : NotesIntent
}