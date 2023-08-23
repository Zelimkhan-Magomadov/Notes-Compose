package zelimkhan.magomadov.notes.ui.note

sealed interface NoteIntent {
    data class TitleChange(val value: String) : NoteIntent
    data class TextChange(val value: String) : NoteIntent
}
