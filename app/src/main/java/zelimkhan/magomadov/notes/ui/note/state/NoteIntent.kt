package zelimkhan.magomadov.notes.ui.note.state

sealed interface NoteIntent {
    data class TitleChange(val value: String) : NoteIntent
    data class TextChange(val value: String) : NoteIntent
}
