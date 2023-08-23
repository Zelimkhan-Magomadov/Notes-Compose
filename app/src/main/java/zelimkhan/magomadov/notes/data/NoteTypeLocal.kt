package zelimkhan.magomadov.notes.data

sealed interface NoteTypeLocal {
    data object Note : NoteTypeLocal
    data object Archived : NoteTypeLocal
    data object Deleted : NoteTypeLocal
}
