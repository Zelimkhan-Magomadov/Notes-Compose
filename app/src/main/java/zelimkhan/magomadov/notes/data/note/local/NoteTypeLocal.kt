package zelimkhan.magomadov.notes.data.note.local

sealed interface NoteTypeLocal {
    data object Note : NoteTypeLocal
    data object Archived : NoteTypeLocal
    data object Deleted : NoteTypeLocal
}