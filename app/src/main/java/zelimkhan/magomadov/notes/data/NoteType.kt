package zelimkhan.magomadov.notes.data

sealed interface NoteType {
    data object Note : NoteType
    data object Archived : NoteType
    data object Deleted : NoteType
}
